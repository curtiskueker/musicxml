drop function if exists pitch_label;

delimiter //
create function pitch_label (
    v_pitch_class int
)
returns varchar(255)
deterministic
begin
    case v_pitch_class
        when 0 then
            return 'C';
        when 1 then
            return 'C-sharp/D-flat';
        when 2 then
            return 'D';
        when 3 then
            return 'D-sharp/E-flat';
        when 4 then
            return 'E';
        when 5 then
            return 'F';
        when 6 then
            return 'F-sharp/G-flat';
        when 7 then
            return 'G';
        when 8 then
            return 'G-sharp/A-flat';
        when 9 then
            return 'A';
        when 10 then
            return 'A-sharp/B-flat';
        when 11 then
            return 'B';
        else
            return null;
    end case;
end //
delimiter ;
