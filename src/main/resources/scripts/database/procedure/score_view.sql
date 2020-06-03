create or replace view score_view as
select
       s.id as score_id,
       p.id as part_id,
       m.measure_number,
       md.id as music_data_id, md.music_data_type, md.voice, md.duration, md.grace_id, char_to_boolean(md.chord) as chord,
       nt.note_type_type, nt.step, nt.pitch_alter, nt.octave,
       t.chromatic
from
     score s, part p, measure m, music_data md
left join note_type nt on nt.id = md.note_type_id
left join transpose t on t.attributes_id = md.id
where
      s.id = p.score_id
      and p.id = m.part_id and m.id = md.measure_id
order by p.ordering, m.ordering, md.ordering;
