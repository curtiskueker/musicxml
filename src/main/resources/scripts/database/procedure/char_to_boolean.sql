drop function if exists char_to_boolean;

delimiter //
create function char_to_boolean (
    boolean_value char(1)
)
returns boolean
deterministic
begin
    return boolean_value is not null and boolean_value = 'Y';
end //
delimiter ;
