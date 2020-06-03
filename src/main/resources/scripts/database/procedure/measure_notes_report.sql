drop procedure if exists measure_notes_report;

delimiter //
create procedure measure_notes_report
	(in v_score_id int,
	in v_measure_number varchar(255),
	in v_step varchar(255),
	in v_pitch_alter int,
	in v_current_transposition int)
proc: begin
    declare v_measure_id int;
    declare v_ordering int;

	create table if not exists report_measure (
	    id int,
	    score_id int,
	    measure_number varchar(255),
	    ordering int
    );
	create table if not exists report_measure_note (
	    measure_id int,
	    step varchar(255),
	    pitch_alter int,
	    transpose int
    );

	select id, ordering from report_measure where score_id = v_score_id and measure_number = v_measure_number into v_measure_id, v_ordering;
    if v_measure_id is null then
        select coalesce(max(id) + 1, 1) from report_measure into v_measure_id;
        select coalesce(max(ordering) + 1, 1) from report_measure where score_id = v_score_id into v_ordering;
        insert into report_measure (id, score_id, measure_number, ordering) values (v_measure_id, v_score_id, v_measure_number, v_ordering);
    end if;
    if pitch_number(v_step, v_pitch_alter, v_current_transposition) is not null then
        insert into report_measure_note (measure_id, step, pitch_alter, transpose)
        values (v_measure_id, v_step, v_pitch_alter, v_current_transposition);
    end if;
end //
delimiter ;

