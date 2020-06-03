drop procedure if exists score_report;

delimiter //
create procedure score_report
(in v_report_name varchar(255), in v_score_id int)
proc: begin
    declare is_end_of_data boolean default false;
    declare is_report_run boolean default false;
    declare v_current_transposition int;
    declare v_current_part_id int default 0;
    declare v_part_id int default 0;
    declare v_current_measure_number varchar(255) default '';
    declare v_measure_number varchar(255);
    declare v_music_data_id int;
    declare v_music_data_type varchar(255);
    declare v_voice varchar(255);
    declare v_current_measure_duration int default 0;
    declare v_previous_duration int default 0;
    declare v_duration int;
    declare v_octave int;
    declare v_previous_octave int;
    declare v_grace_id int;
    declare is_chord boolean;
    declare v_note_type_type varchar(255);
    declare v_chromatic int;
    declare v_step varchar(255);
    declare v_previous_step varchar(255);
    declare v_pitch_alter int;
    declare v_previous_pitch_alter int;

    declare c_score_items cursor for
        select part_id, measure_number, music_data_id, music_data_type, voice, duration, grace_id, chord, note_type_type, step, pitch_alter, octave, chromatic
        from score_view
        where score_id = v_score_id;

    declare continue handler for not found set is_end_of_data = true;

    create table if not exists report_run (
        score_id int,
        report_name varchar(255)
    );

    create table if not exists report_previous_note (
        voice varchar(255),
        octave int,
        step varchar(255),
        pitch_alter int
    );

    if exists (select score_id from report_run where score_id = v_score_id and report_name = v_report_name) then leave proc; end if;

    open c_score_items;
    score_items_loop: loop
        fetch c_score_items into
            v_part_id, v_measure_number, v_music_data_id, v_music_data_type,
            v_voice, v_duration, v_grace_id, is_chord, v_note_type_type,
            v_step, v_pitch_alter, v_octave, v_chromatic;
        if is_end_of_data then leave score_items_loop; end if;
        if v_part_id != v_current_part_id then
            set v_current_transposition = null;
            set v_current_part_id = v_part_id;
            delete from report_previous_note;
        end if;
        if v_measure_number != v_current_measure_number then
            set v_current_measure_number = v_measure_number;
            set v_current_measure_duration = 0;
        end if;
        case v_music_data_type
            when 'attributes' then
                if v_chromatic is not null then set v_current_transposition = v_chromatic; end if;
            when 'backup' then
                set v_current_measure_duration = v_current_measure_duration - v_duration;
            when 'forward' then
                set v_current_measure_duration = v_current_measure_duration + v_duration;
            when 'note' then
                if is_tied_note(v_music_data_id) then iterate score_items_loop; end if;
                if v_note_type_type = 'pitch' then
                    if not is_chord then
                        if exists (select voice from report_previous_note where voice = v_voice) then
                            select octave, step, pitch_alter into v_previous_octave, v_previous_step, v_previous_pitch_alter
                            from report_previous_note where voice = v_voice;
                        else
                            set v_previous_octave = null;
                            set v_previous_step = null;
                            set v_previous_pitch_alter = null;
                        end if;
                        if v_report_name = 'interval count' then
                            call interval_count_report(v_score_id, v_step, v_previous_step, v_pitch_alter, v_previous_pitch_alter, v_octave, v_previous_octave);
                            set is_report_run = true;
                        end if;
                        if exists (select voice from report_previous_note where voice = v_voice) then
                            update report_previous_note set octave = v_octave, step = v_step, pitch_alter = v_pitch_alter where voice = v_voice;
                        else
                            insert into report_previous_note (voice, octave, step, pitch_alter) values (v_voice, v_octave, v_step, v_pitch_alter);
                        end if;
                    end if;
                elseif v_note_type_type = 'rest' then
                    delete from report_previous_note where voice = v_voice;
                end if;
                if is_chord then set v_current_measure_duration = v_current_measure_duration - v_previous_duration; end if;
                if v_report_name = 'measure notes' then
                    if v_grace_id is null and v_current_measure_duration = 0 then
                        call measure_notes_report(v_score_id, v_measure_number, v_step, v_pitch_alter, v_current_transposition);
                        set is_report_run = true;
                    end if;
                elseif v_report_name = 'pitch count' then
                    call pitch_count_report(v_score_id, v_step, v_pitch_alter, v_current_transposition);
                    set is_report_run = true;
                end if;
                set v_current_measure_duration = v_current_measure_duration + v_duration;
                set v_previous_duration = v_duration;
            else begin end;
            end case;
    end loop;
    close c_score_items;

    if is_report_run then insert into report_run (score_id, report_name) values (v_score_id, v_report_name); end if;
    drop table report_previous_note;
end //
delimiter ;
