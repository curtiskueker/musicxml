drop function if exists pitch_alter_label;

delimiter //
create function pitch_alter_label (
    v_pitch_alter int
)
    returns varchar(255)
    deterministic
begin
    case v_pitch_alter
        when -1 then
            return 'flat';
        when 1 then
            return  'sharp';
        else
            return '';
        end case;
end //
delimiter ;
