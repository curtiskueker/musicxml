drop function if exists pitch_number;

delimiter //
create function pitch_number (
	v_step varchar(255),
	v_pitch_alter int,
	v_transpose int
)
returns int
deterministic
begin
	declare v_pitch_number int default 0;
	declare v_mod_number int default 12;

	if v_step is null then return null; end if;

	case v_step
		when 'A' then 
			set v_pitch_number = 9;
		when 'B' then 
			set v_pitch_number = 11;
		when 'C' then 
			set v_pitch_number = 0;
		when 'D' then 
			set v_pitch_number = 2;
		when 'E' then 
			set v_pitch_number = 4;
		when 'F' then 
			set v_pitch_number = 5;
		when 'G' then 
			set v_pitch_number = 7;
		else
			return null;
	end case;
	if v_pitch_alter is not null then set v_pitch_number = v_pitch_number + v_pitch_alter; end if;
	if v_transpose is not null then set v_pitch_number = v_pitch_number + v_transpose; end if;
	return (mod(v_pitch_number + v_mod_number, v_mod_number));
end //
delimiter ;

