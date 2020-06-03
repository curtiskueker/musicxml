drop procedure if exists interval_count_report;

delimiter //
create procedure interval_count_report
(in v_score_id int,
 in v_step varchar(255),
 in v_previous_step varchar(255),
 in v_pitch_alter int,
 in v_previous_pitch_alter int,
 in v_octave int,
 in v_previous_octave int)
proc: begin
    declare v_interval_count int;
    declare v_interval_number int;

    create table if not exists report_interval_counts (
                                                   score_id int,
                                                   pitch_interval int,
                                                   interval_count int
    );

    if v_step is null or v_previous_step is null then leave proc; end if;

    set v_interval_number = pitch_number(v_step, v_pitch_alter, null) - pitch_number(v_previous_step, v_previous_pitch_alter, null);
    if v_octave is not null and v_previous_octave is not null then
        set v_interval_number = v_interval_number + ((v_octave * 12) - (v_previous_octave * 12));
    end if;
    select interval_count into v_interval_count from report_interval_counts where score_id = v_score_id and pitch_interval = v_interval_number;
    if v_interval_count is null then
        insert into report_interval_counts (score_id, pitch_interval, interval_count) values (v_score_id, v_interval_number, 1);
    else
        set v_interval_count = v_interval_count + 1;
        update report_interval_counts set interval_count = v_interval_count where score_id = v_score_id and pitch_interval = v_interval_number;
    end if;
end //
delimiter ;

