drop procedure if exists pitch_count_report;

delimiter //
create procedure pitch_count_report
	(in v_score_id int,
	in v_step varchar(255),
	in v_pitch_alter int,
	in v_transposition int)
proc: begin
	declare v_pitch_count int;
	declare v_pitch_number int;

	create table if not exists report_pitch_counts (
	    score_id int,
	    pitch int,
	    pitch_count int
    );

    set v_pitch_number = pitch_number(v_step, v_pitch_alter, v_transposition);
    if v_pitch_number is null then leave proc; end if;

    select pitch_count into v_pitch_count from report_pitch_counts where score_id = v_score_id and pitch = v_pitch_number;
    if v_pitch_count is null then
        insert into report_pitch_counts (score_id, pitch, pitch_count) values (v_score_id, v_pitch_number, 1);
    else
        set v_pitch_count = v_pitch_count + 1;
        update report_pitch_counts set pitch_count = v_pitch_count where score_id = v_score_id and pitch = v_pitch_number;
    end if;
end //
delimiter ;

