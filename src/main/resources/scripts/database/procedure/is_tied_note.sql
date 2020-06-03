drop function if exists is_tied_note;

delimiter //
create function is_tied_note (
    v_note_id int
)
returns boolean
not deterministic
begin
    declare is_end_of_data boolean default false;
    declare v_type varchar(255);
    declare is_tied boolean default false;

    declare c_note_ties cursor for
        select type from tie where note_id = v_note_id;

    declare continue handler for not found set is_end_of_data = true;

    open c_note_ties;
    ties_loop: loop
        fetch c_note_ties into v_type;
        if is_end_of_data then leave ties_loop; end if;
        if v_type = 'STOP' or v_type = 'CONTINUE' then
            set is_tied = true;
            leave ties_loop;
        end if;
    end loop;
    close c_note_ties;

    return is_tied;
end //
delimiter ;
