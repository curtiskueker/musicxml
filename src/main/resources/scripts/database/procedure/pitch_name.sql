drop function if exists pitch_name;

delimiter //
create function pitch_name (
	v_step varchar(255),
	v_pitch_alter int
)
returns varchar(255)
deterministic
begin
    if v_step is null then return null; end if;
    if v_pitch_alter is null then return v_step; end if;

    return concat(v_step, '-', pitch_alter_label(v_pitch_alter));
end //
delimiter ;
