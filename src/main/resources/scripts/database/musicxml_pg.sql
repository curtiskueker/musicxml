
    create table accidental (
       id  serial not null,
        accidental_type varchar(255),
        cautionary boolean,
        editorial boolean,
        level_display_id int4,
        print_style_id int4,
        primary key (id)
    );

    create table accord (
       id  serial not null,
        string int4,
        tuning_id int4,
        scordatura_id int4 not null,
        primary key (id)
    );

    create table appearance (
       id  serial not null,
        primary key (id)
    );

    create table articulation (
       articulation_type varchar(31) not null,
        id  serial not null,
        breath_mark_value varchar(255),
        placement varchar(255),
        type varchar(255),
        print_style_id int4,
        placement_id int4,
        line_id int4,
        placement_text_id int4,
        articulations_id int4 not null,
        primary key (id)
    );

    create table bar_style_color (
       id  serial not null,
        bar_style varchar(255),
        color varchar(255),
        primary key (id)
    );

    create table barline_repeat (
       id  serial not null,
        direction varchar(255),
        times int4,
        winged varchar(255),
        primary key (id)
    );

    create table barre (
       id  serial not null,
        color varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table bass (
       id  serial not null,
        bass_alter_id int4,
        bass_step_id int4,
        primary key (id)
    );

    create table bass_alter (
       id  serial not null,
        location varchar(255),
        print_object boolean,
        semitones numeric(12, 4),
        print_style_id int4,
        primary key (id)
    );

    create table bass_step (
       id  serial not null,
        step varchar(255),
        text varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table beam (
       id  serial not null,
        color varchar(255),
        fan varchar(255),
        number int4,
        repeater boolean,
        type varchar(255),
        note_id int4 not null,
        primary key (id)
    );

    create table beat_unit (
       id  serial not null,
        beat_unit varchar(255),
        beat_unit_dots int4,
        primary key (id)
    );

    create table bend_sound (
       id  serial not null,
        accelerate boolean,
        beats numeric(12, 4),
        first_beat numeric(12, 4),
        last_beat numeric(12, 4),
        primary key (id)
    );

    create table bezier (
       id  serial not null,
        bezier_offset numeric(12, 4),
        bezier_offset2 numeric(12, 4),
        bezier_x numeric(12, 4),
        bezier_x2 numeric(12, 4),
        bezier_y numeric(12, 4),
        bezier_y2 numeric(12, 4),
        primary key (id)
    );

    create table cancel (
       id  serial not null,
        fifths int4,
        location varchar(255),
        primary key (id)
    );

    create table clef (
       id  serial not null,
        additional boolean,
        after_barline boolean,
        clef_octave_change int4,
        line int4,
        number int4,
        print_object boolean,
        sign varchar(255),
        size varchar(255),
        print_style_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table credit (
       id  serial not null,
        page int4,
        score_header_id int4 not null,
        primary key (id)
    );

    create table credit_display (
       credit_display_type varchar(31) not null,
        id  serial not null,
        formatted_text_id int4,
        image_id int4,
        credit_id int4 not null,
        primary key (id)
    );

    create table credit_type (
       id  serial not null,
        type varchar(255),
        credit_id int4 not null,
        primary key (id)
    );

    create table dashed_formatting (
       id  serial not null,
        dash_length numeric(12, 4),
        space_length numeric(12, 4),
        primary key (id)
    );

    create table defaults (
       id  serial not null,
        appearance_id int4,
        layout_id int4,
        music_font_id int4,
        defaults_id int4,
        word_font_id int4,
        primary key (id)
    );

    create table degree (
       id  serial not null,
        print_object boolean,
        degree_alter_id int4,
        degree_type_id int4,
        degree_value_id int4,
        harmony_chord_id int4 not null,
        primary key (id)
    );

    create table degree_alter (
       id  serial not null,
        plus_minus boolean,
        semitones numeric(12, 4),
        print_style_id int4,
        primary key (id)
    );

    create table degree_type (
       id  serial not null,
        text varchar(255),
        value varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table degree_value (
       id  serial not null,
        symbol varchar(255),
        text varchar(255),
        value int4,
        print_style_id int4,
        primary key (id)
    );

    create table direction_offset (
       id  serial not null,
        divisions numeric(12, 4),
        sound boolean,
        primary key (id)
    );

    create table direction_type (
       direction_type varchar(31) not null,
        id  serial not null,
        enclosure varchar(255),
        type varchar(255),
        accordion_high boolean,
        accordion_low boolean,
        accordion_middle int4,
        color varchar(255),
        line_type varchar(255),
        niente boolean,
        number int4,
        spread numeric(12, 4),
        value varchar(255),
        line boolean,
        sign boolean,
        halign varchar(255),
        source varchar(255),
        valign_image varchar(255),
        beater_value varchar(255),
        tip varchar(255),
        justify varchar(255),
        parentheses boolean,
        metronome_relation varchar(255),
        principal_voice varchar(255),
        symbol varchar(255),
        placement varchar(255),
        print_object boolean,
        stick_material varchar(255),
        stick_type varchar(255),
        end_length numeric(12, 4),
        line_end varchar(255),
        size int4,
        direction_type_list_id int4,
        print_style_align_id int4,
        fomatted_text_id int4,
        dashed_formatting_id int4,
        position_id int4,
        text_decoration_id int4,
        print_style_id int4,
        beat_unit_1_id int4,
        beat_unit_2_id int4,
        per_minute_id int4,
        primary key (id)
    );

    create table direction_type_list (
       id  serial not null,
        direction_id int4,
        primary key (id)
    );

    create table directive (
       id  serial not null,
        lang varchar(255),
        value varchar(255),
        print_style_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table distance (
       id  serial not null,
        type varchar(255),
        value numeric(12, 4),
        appearance_id int4 not null,
        primary key (id)
    );

    create table dot (
       id  serial not null,
        placement_id int4,
        note_id int4 not null,
        primary key (id)
    );

    create table dynamics_marking (
       id  serial not null,
        dynamics_type varchar(255),
        dynamics_id int4 not null,
        primary key (id)
    );

    create table editorial (
       id  serial not null,
        footnote_id int4,
        level_id int4,
        primary key (id)
    );

    create table editorial_voice (
       id  serial not null,
        voice varchar(255),
        footnote_id int4,
        level_id int4,
        primary key (id)
    );

    create table editorial_voice_direction (
       id  serial not null,
        voice varchar(255),
        footnote_id int4,
        level_id int4,
        primary key (id)
    );

    create table element_position (
       id  serial not null,
        element varchar(255),
        position int4,
        primary key (id)
    );

    create table encoding (
       encoding_type varchar(31) not null,
        id  serial not null,
        attribute varchar(255),
        element varchar(255),
        type boolean,
        value varchar(255),
        software varchar(255),
        encoding_date timestamp,
        encoding_description varchar(255),
        encoder_id int4,
        identification_id int4 not null,
        primary key (id)
    );

    create table ending (
       id  serial not null,
        end_length numeric(12, 4),
        number varchar(255),
        print_object boolean,
        text_x numeric(12, 4),
        text_y numeric(12, 4),
        type varchar(255),
        value varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table feature (
       id  serial not null,
        type varchar(255),
        value varchar(255),
        grouping_id int4 not null,
        primary key (id)
    );

    create table figure (
       id  serial not null,
        extend_id int4,
        figure_number_id int4,
        prefix_id int4,
        suffix_id int4,
        figured_bass_id int4 not null,
        primary key (id)
    );

    create table first_fret (
       id  serial not null,
        location varchar(255),
        text varchar(255),
        value int4,
        primary key (id)
    );

    create table font (
       id  serial not null,
        font_family varchar(255),
        font_style varchar(255),
        font_weight varchar(255),
        font_size_id int4,
        primary key (id)
    );

    create table font_size (
       id  serial not null,
        css_font_size varchar(255),
        font_size numeric(12, 4),
        primary key (id)
    );

    create table formatted_text (
       id  serial not null,
        value text,
        text_formatting_id int4,
        primary key (id)
    );

    create table frame (
       id  serial not null,
        color varchar(255),
        frame_frets int4,
        frame_strings int4,
        halign varchar(255),
        height numeric(12, 4),
        unplayed varchar(255),
        valign_image varchar(255),
        width numeric(12, 4),
        first_fret_id int4,
        position_id int4,
        primary key (id)
    );

    create table frame_note (
       id  serial not null,
        barre_id int4,
        fingering_id int4,
        fret_id int4,
        string_id int4,
        frame_id int4 not null,
        primary key (id)
    );

    create table full_note (
       id  serial not null,
        chord boolean,
        full_note_type_id int4,
        primary key (id)
    );

    create table full_note_type (
       full_note_type_name varchar(31) not null,
        id  serial not null,
        pitch_alter numeric(12, 4),
        octave int4,
        step varchar(255),
        display_octave int4,
        display_step varchar(255),
        measure boolean,
        primary key (id)
    );

    create table grace (
       id  serial not null,
        make_time numeric(12, 4),
        slash boolean,
        steal_time_following numeric(12, 4),
        steal_time_previous numeric(12, 4),
        primary key (id)
    );

    create table group_barline (
       id  serial not null,
        color varchar(255),
        group_barline_type varchar(255),
        primary key (id)
    );

    create table group_name (
       id  serial not null,
        group_name varchar(255),
        justify varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table group_symbol (
       id  serial not null,
        color varchar(255),
        group_symbol_type varchar(255),
        position_id int4,
        primary key (id)
    );

    create table harmony_chord (
       harmony_chord_type varchar(31) not null,
        id  serial not null,
        bass_id int4,
        inversion_id int4,
        kind_id int4,
        root_alter_id int4,
        root_step_id int4,
        function_id int4,
        harmony_id int4 not null,
        primary key (id)
    );

    create table identification (
       id  serial not null,
        source varchar(255),
        miscellaneous_id int4,
        primary key (id)
    );

    create table instrument_type (
       instrument_type varchar(31) not null,
        id  serial not null,
        value int4,
        primary key (id)
    );

    create table interchangeable (
       id  serial not null,
        time_separator varchar(255),
        symbol varchar(255),
        time_relation varchar(255),
        time_signature_id int4,
        primary key (id)
    );

    create table inversion (
       id  serial not null,
        value int4,
        print_style_id int4,
        primary key (id)
    );

    create table key_octave (
       id  serial not null,
        cancel boolean,
        number int4,
        octave int4,
        key_id int4 not null,
        primary key (id)
    );

    create table key_signature (
       key_type varchar(31) not null,
        id  serial not null,
        number int4,
        print_object boolean,
        fifths int4,
        mode varchar(255),
        print_style_id int4,
        cancel_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table kind (
       id  serial not null,
        bracket_degrees boolean,
        halign varchar(255),
        kind_value varchar(255),
        parentheses_degrees boolean,
        stack_degrees boolean,
        text varchar(255),
        use_symbols boolean,
        valign varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table layout (
       id  serial not null,
        page_layout_id int4,
        systemm_layout_id int4,
        primary key (id)
    );

    create table level (
       id  serial not null,
        reference boolean,
        value varchar(255),
        level_display_id int4,
        primary key (id)
    );

    create table level_display (
       id  serial not null,
        bracket boolean,
        parentheses boolean,
        size varchar(255),
        primary key (id)
    );

    create table line (
       id  serial not null,
        line_shape varchar(255),
        line_type varchar(255),
        placement varchar(255),
        dashed_formatting_id int4,
        print_style_id int4,
        primary key (id)
    );

    create table line_width (
       id  serial not null,
        line_width_type varchar(255),
        value numeric(12, 4),
        appearance_id int4 not null,
        primary key (id)
    );

    create table link_attributes (
       id  serial not null,
        actuate varchar(255),
        href varchar(255),
        link_role varchar(255),
        link_show varchar(255),
        title varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table lyric (
       id  serial not null,
        color varchar(255),
        end_line boolean,
        end_paragraph boolean,
        justify varchar(255),
        name varchar(255),
        number varchar(255),
        placement varchar(255),
        print_object boolean,
        editorial_id int4,
        lyric_item_id int4,
        position_id int4,
        note_id int4 not null,
        primary key (id)
    );

    create table lyric_font (
       id  serial not null,
        name varchar(255),
        number varchar(255),
        font_id int4,
        defaults_id int4 not null,
        primary key (id)
    );

    create table lyric_item (
       lyric_item_type varchar(31) not null,
        id  serial not null,
        type varchar(255),
        print_style_id int4,
        extend_id int4,
        primary key (id)
    );

    create table lyric_language (
       id  serial not null,
        lang varchar(255),
        name varchar(255),
        number varchar(255),
        defaults_id int4 not null,
        primary key (id)
    );

    create table lyric_syllable (
       id  serial not null,
        syllabic varchar(255),
        lyric_elision_id int4,
        text_data_id int4,
        lyric_text_id int4 not null,
        primary key (id)
    );

    create table margins (
       id  serial not null,
        bottom_margin numeric(12, 4),
        left_margin numeric(12, 4),
        right_margin numeric(12, 4),
        top_margin numeric(12, 4),
        primary key (id)
    );

    create table measure (
       id  serial not null,
        ordering int4,
        implicit boolean,
        non_controlling boolean,
        number varchar(255),
        width numeric(12, 4),
        part_id int4 not null,
        primary key (id)
    );

    create table measure_layout (
       id  serial not null,
        measure_distance numeric(12, 4),
        primary key (id)
    );

    create table measure_style (
       measure_style_type varchar(31) not null,
        id  serial not null,
        color varchar(255),
        number int4,
        use_symbols boolean,
        value int4,
        type varchar(255),
        use_dots boolean,
        use_stems boolean,
        slashes int4,
        font_id int4,
        slash_group_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table metronome_beam (
       id  serial not null,
        beam_type varchar(255),
        number int4,
        metronome_note_id int4 not null,
        primary key (id)
    );

    create table metronome_note (
       id  serial not null,
        metronome_dots int4,
        metronome_type varchar(255),
        metronome_tuplet_id int4,
        note_metronome_2_id int4 not null,
        note_metronome_1_id int4 not null,
        primary key (id)
    );

    create table metronome_tuplet (
       id  serial not null,
        bracket boolean,
        show_number varchar(255),
        type varchar(255),
        time_modification_id int4,
        primary key (id)
    );

    create table midi_device (
       id  serial not null,
        midi_device_id varchar(255),
        port int4,
        value varchar(255),
        primary key (id)
    );

    create table midi_instrument (
       id  serial not null,
        elevation numeric(12, 4),
        midi_bank int4,
        midi_channel int4,
        midi_instrumentj_id varchar(255),
        midi_name varchar(255),
        midi_program int4,
        midi_unpitched int4,
        pan numeric(12, 4),
        volume numeric(12, 4),
        primary key (id)
    );

    create table miscellaneous (
       id  serial not null,
        primary key (id)
    );

    create table miscellaneous_field (
       id  serial not null,
        name varchar(255),
        value varchar(255),
        miscellaneous_id int4 not null,
        primary key (id)
    );

    create table music_data (
       music_data_type varchar(31) not null,
        id  serial not null,
        ordering int4,
        duration numeric(12, 4),
        coda varchar(255),
        dacapo boolean,
        dalsegno varchar(255),
        damper_pedal varchar(255),
        divisions numeric(12, 4),
        dynamics numeric(12, 4),
        elevation numeric(12, 4),
        fine varchar(255),
        forward_repeat boolean,
        pan numeric(12, 4),
        pizzicato boolean,
        segno varchar(255),
        soft_pedal varchar(255),
        sostenuto_pedal varchar(255),
        tempo numeric(12, 4),
        time_only varchar(255),
        tocoda varchar(255),
        parentheses boolean,
        instruments int4,
        staves int4,
        placement varchar(255),
        print_frame boolean,
        print_object boolean,
        staff int4,
        type varchar(255),
        location varchar(255),
        blank_page int4,
        measure_numbering_value varchar(255),
        new_page boolean,
        new_system boolean,
        page_number varchar(255),
        staff_spacing numeric(12, 4),
        number varchar(255),
        number_of varchar(255),
        bookmark_id varchar(255),
        name varchar(255),
        attack_length numeric(12, 4),
        color varchar(255),
        cue boolean,
        end_dynamics numeric(12, 4),
        instrument varchar(255),
        release_length numeric(12, 4),
        directive boolean,
        measure_id int4,
        editorial_id int4,
        offset_id int4,
        print_style_id int4,
        printout_id int4,
        part_symbol_id int4,
        frame_id int4,
        bar_style_id int4,
        coda_print_id int4,
        ending_id int4,
        repeat_id int4,
        segno_print_id int4,
        wavy_line_id int4,
        layout_id int4,
        measure_layout_id int4,
        part_abbreviation_display_id int4,
        part_name_display_id int4,
        print_style_align_id int4,
        credit_display_id int4,
        element_position_id int4,
        editorial_voice_id int4,
        link_attributes_id int4,
        position_id int4,
        accidental_id int4,
        font_id int4,
        full_note_id int4,
        grace_id int4,
        notehead_id int4,
        notehead_text_id int4,
        play_id int4,
        stem_id int4,
        time_modification_id int4,
        type_id int4,
        editorial_voice_direction_id int4,
        sound_id int4,
        primary key (id)
    );

    create table name_display (
       id  serial not null,
        print_object boolean,
        primary key (id)
    );

    create table non_traditional_key_type (
       id  serial not null,
        key_accidental varchar(255),
        key_alter numeric(12, 4),
        key_step varchar(255),
        non_traditional_key_id int4 not null,
        primary key (id)
    );

    create table notation (
       notation_type varchar(31) not null,
        id  serial not null,
        color varchar(255),
        number int4,
        placement varchar(255),
        type varchar(255),
        print_object boolean,
        value varchar(255),
        line_type varchar(255),
        connection_type varchar(255),
        orientation varchar(255),
        fermata_shape varchar(255),
        direction varchar(255),
        bracket boolean,
        line_shape varchar(255),
        show_number varchar(255),
        show_type varchar(255),
        accidental_type varchar(255),
        notations_id int4,
        position_id int4,
        print_style_id int4,
        dashed_formatting_id int4,
        bezier_id int4,
        bend_sound_id int4,
        barline_id int4,
        tuplet_actual_id int4,
        tuplet_normal_id int4,
        dynamics_id int4,
        primary key (id)
    );

    create table notations (
       id  serial not null,
        print_object boolean,
        editorial_id int4,
        note_id int4 not null,
        primary key (id)
    );

    create table note_size (
       id  serial not null,
        type varchar(255),
        value numeric(12, 4),
        appearance_id int4 not null,
        primary key (id)
    );

    create table note_type (
       id  serial not null,
        size varchar(255),
        value varchar(255),
        primary key (id)
    );

    create table notehead (
       id  serial not null,
        color varchar(255),
        filled boolean,
        parentheses boolean,
        type varchar(255),
        font_id int4,
        primary key (id)
    );

    create table notehead_text (
       id  serial not null,
        primary key (id)
    );

    create table ornament (
       ornament_type varchar(31) not null,
        id  serial not null,
        placement varchar(255),
        slash boolean,
        color varchar(255),
        number int4,
        type varchar(255),
        tremolo_marks int4,
        approach varchar(255),
        departure varchar(255),
        long_mordent boolean,
        ornaments_id int4,
        placement_text_id int4,
        print_style_id int4,
        trill_sound_id int4,
        position_id int4,
        placement_id int4,
        primary key (id)
    );

    create table ornament_accidental (
       id  serial not null,
        accidental_mark_id int4,
        ornaments_id int4 not null,
        primary key (id)
    );

    create table other_appearance (
       id  serial not null,
        type varchar(255),
        value varchar(255),
        appearance_id int4 not null,
        primary key (id)
    );

    create table page_layout (
       id  serial not null,
        page_height numeric(12, 4),
        page_width numeric(12, 4),
        primary key (id)
    );

    create table page_margins (
       id  serial not null,
        margin_type_key varchar(255),
        type varchar(255),
        margins_id int4,
        page_layout_id int4 not null,
        primary key (id)
    );

    create table part (
       id  serial not null,
        ordering int4,
        part_id varchar(255),
        score_id int4 not null,
        primary key (id)
    );

    create table part_item (
       part_item_type varchar(31) not null,
        id  serial not null,
        ordering int4,
        group_time boolean,
        number varchar(255),
        type varchar(255),
        score_part_id varchar(255),
        editorial_id int4,
        group_abbreviation_id int4,
        group_abbreviation_display_id int4,
        group_barline_id int4,
        group_name_id int4,
        group_name_display_id int4,
        group_symbol_id int4,
        identification_id int4,
        part_abbreviation_id int4,
        part_abbreviation_display_id int4,
        part_name_id int4,
        part_name_display_id int4,
        part_list_id int4 not null,
        primary key (id)
    );

    create table part_list (
       id  serial not null,
        primary key (id)
    );

    create table part_name (
       id  serial not null,
        part_name varchar(255),
        part_name_justify varchar(255),
        part_name_print_object boolean,
        part_name_print_style_id int4,
        primary key (id)
    );

    create table part_symbol (
       id  serial not null,
        bottom_staff int4,
        color varchar(255),
        group_symbol_type varchar(255),
        top_staff int4,
        position_id int4,
        primary key (id)
    );

    create table pedal_tuning (
       id  serial not null,
        pedal_alter numeric(12, 4),
        pedal_step varchar(255),
        harp_pedals_id int4 not null,
        primary key (id)
    );

    create table per_minute (
       id  serial not null,
        per_minute varchar(255),
        font_id int4,
        primary key (id)
    );

    create table placement (
       id  serial not null,
        placement varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table placement_text (
       id  serial not null,
        placement varchar(255),
        value varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table play (
       id  serial not null,
        play_id varchar(255),
        primary key (id)
    );

    create table play_type (
       play_type_type varchar(31) not null,
        id  serial not null,
        value varchar(255),
        type varchar(255),
        play_id int4,
        primary key (id)
    );

    create table position (
       id  serial not null,
        default_x numeric(12, 4),
        default_y numeric(12, 4),
        relative_x numeric(12, 4),
        relative_y numeric(12, 4),
        primary key (id)
    );

    create table print_object_style_align (
       id  serial not null,
        print_object boolean,
        print_style_align_id int4,
        primary key (id)
    );

    create table print_style (
       id  serial not null,
        color varchar(255),
        font_id int4,
        position_id int4,
        primary key (id)
    );

    create table print_style_align (
       id  serial not null,
        halign varchar(255),
        valign varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table printout (
       id  serial not null,
        print_dot boolean,
        print_lyric boolean,
        print_object boolean,
        print_spacing boolean,
        primary key (id)
    );

    create table root_alter (
       id  serial not null,
        location varchar(255),
        print_object boolean,
        semitones numeric(12, 4),
        print_style_id int4,
        primary key (id)
    );

    create table root_step (
       id  serial not null,
        step varchar(255),
        text varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table scaling (
       id  serial not null,
        millimeters numeric(12, 4),
        tenths numeric(12, 4),
        primary key (id)
    );

    create table score (
       id  serial not null,
        score_name varchar(255),
        version varchar(255),
        score_header_id int4,
        primary key (id)
    );

    create table score_header (
       id  serial not null,
        movement_number varchar(255),
        movement_title varchar(255),
        defaults_id int4,
        identification_id int4,
        part_list_id int4,
        work_id int4,
        primary key (id)
    );

    create table score_instrument (
       id  serial not null,
        instrument_abbreviation varchar(255),
        instrument_name varchar(255),
        instrument_sound varchar(255),
        score_instrument_id varchar(255),
        instrument_type_id int4,
        virtual_instrument_id int4,
        score_part_id int4 not null,
        primary key (id)
    );

    create table score_midi (
       id  serial not null,
        midi_device_id int4,
        midi_instrument_id int4,
        score_part_id int4 not null,
        primary key (id)
    );

    create table score_part_group (
       id  serial not null,
        group_name varchar(255),
        score_part_id int4 not null,
        primary key (id)
    );

    create table slash_group (
       id  serial not null,
        slash_dots int4,
        slash_type varchar(255),
        primary key (id)
    );

    create table sound_midi (
       id  serial not null,
        midi_device_id int4,
        midi_instrument_id int4,
        play_id int4,
        sound_id int4 not null,
        primary key (id)
    );

    create table staff_details (
       id  serial not null,
        capo int4,
        number int4,
        print_object boolean,
        print_spacing boolean,
        show_frets varchar(255),
        staff_lines int4,
        staff_size numeric(12, 4),
        staff_type varchar(255),
        attributes_id int4 not null,
        primary key (id)
    );

    create table staff_layout (
       id  serial not null,
        number int4,
        staff_distance numeric(12, 4),
        layout_id int4 not null,
        primary key (id)
    );

    create table staff_tuning (
       id  serial not null,
        line int4,
        tuning_id int4,
        staff_details_id int4 not null,
        primary key (id)
    );

    create table stem (
       id  serial not null,
        color varchar(255),
        type varchar(255),
        position_id int4,
        primary key (id)
    );

    create table style_text (
       id  serial not null,
        value varchar(255),
        print_style_id int4,
        primary key (id)
    );

    create table system_dividers (
       id  serial not null,
        left_divider_id int4,
        right_divider_id int4,
        primary key (id)
    );

    create table system_layout (
       id  serial not null,
        left_margin numeric(12, 4),
        right_margin numeric(12, 4),
        system_distance numeric(12, 4),
        top_system_distance numeric(12, 4),
        system_dividers_id int4,
        primary key (id)
    );

    create table technical (
       technical_type varchar(31) not null,
        id  serial not null,
        placement varchar(255),
        string_number int4,
        alternate boolean,
        substitution boolean,
        value varchar(255),
        number int4,
        type varchar(255),
        color varchar(255),
        arrow_direction varchar(255),
        arrow_style varchar(255),
        circular_arrow varchar(255),
        hole_closed_location varchar(255),
        hole_close_type varchar(255),
        hole_shape varchar(255),
        hole_type varchar(255),
        bend_alter numeric(12, 4),
        bend_type varchar(255),
        handbell_type varchar(255),
        harmonic_pitch varchar(255),
        harmonic_type varchar(255),
        print_object boolean,
        technicals_id int4,
        print_style_id int4,
        placement_id int4,
        font_id int4,
        placement_text_id int4,
        bend_sound_id int4,
        with_bar_id int4,
        primary key (id)
    );

    create table text_data (
       id  serial not null,
        color varchar(255),
        lang varchar(255),
        letter_spacing varchar(255),
        text_direction varchar(255),
        text_rotation numeric(12, 4),
        value varchar(255),
        font_id int4,
        text_decoration_id int4,
        primary key (id)
    );

    create table text_decoration (
       id  serial not null,
        line_through int4,
        overline int4,
        underline int4,
        primary key (id)
    );

    create table text_display (
       text_type varchar(31) not null,
        id  serial not null,
        accidental_type varchar(255),
        name_display_id int4,
        notehead_text_id int4,
        text_formatting_id int4,
        display_text_id int4,
        primary key (id)
    );

    create table text_font_color (
       id  serial not null,
        color varchar(255),
        lang varchar(255),
        letter_spacing varchar(255),
        text_direction varchar(255),
        text_rotation numeric(12, 4),
        value varchar(255),
        font_id int4,
        text_decoration_id int4,
        primary key (id)
    );

    create table text_formatting (
       id  serial not null,
        enclosure varchar(255),
        justify varchar(255),
        lang varchar(255),
        letter_spacing varchar(255),
        line_height varchar(255),
        space varchar(255),
        text_direction varchar(255),
        text_rotation numeric(12, 4),
        print_style_align_id int4,
        text_decoration_id int4,
        primary key (id)
    );

    create table tie (
       id  serial not null,
        time_only varchar(255),
        type varchar(255),
        note_id int4 not null,
        primary key (id)
    );

    create table time (
       time_type varchar(31) not null,
        id  serial not null,
        number int4,
        print_object boolean,
        time_separator varchar(255),
        symbol varchar(255),
        value varchar(255),
        print_style_align_id int4,
        interchangeable_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table time_modification (
       id  serial not null,
        actual_notes int4,
        normal_dots int4,
        normal_notes int4,
        normal_type varchar(255),
        primary key (id)
    );

    create table time_signature_type (
       id  serial not null,
        beat_type varchar(255),
        beats varchar(255),
        time_signature_id int4 not null,
        primary key (id)
    );

    create table transpose (
       id  serial not null,
        chromatic numeric(12, 4),
        diatonic int4,
        doubled boolean,
        number int4,
        octave_change int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table trill_sound (
       id  serial not null,
        accelerate boolean,
        beats numeric(12, 4),
        last_beat numeric(12, 4),
        second_beat numeric(12, 4),
        start_note varchar(255),
        trill_step varchar(255),
        two_note_turn varchar(255),
        primary key (id)
    );

    create table tuning (
       id  serial not null,
        tuning_alter numeric(12, 4),
        tuning_octave int4,
        tuning_step varchar(255),
        primary key (id)
    );

    create table tuplet_dot (
       id  serial not null,
        color varchar(255),
        font_id int4,
        tuplet_portion_id int4 not null,
        primary key (id)
    );

    create table tuplet_number (
       id  serial not null,
        color varchar(255),
        value int4,
        font_id int4,
        primary key (id)
    );

    create table tuplet_portion (
       id  serial not null,
        tuplet_number_id int4,
        tuplet_type_id int4,
        primary key (id)
    );

    create table tuplet_type (
       id  serial not null,
        color varchar(255),
        note_type_value varchar(255),
        font_id int4,
        primary key (id)
    );

    create table typed_text (
       id  serial not null,
        type varchar(255),
        value varchar(255),
        creator_id int4,
        relation_id int4,
        rights_id int4,
        primary key (id)
    );

    create table virtual_instrument (
       id  serial not null,
        virtual_library varchar(255),
        virtual_name varchar(255),
        primary key (id)
    );

    create table work (
       id  serial not null,
        work_number varchar(255),
        work_title varchar(255),
        opus_id int4,
        primary key (id)
    );

    create table xml_comment (
       id  serial not null,
        data varchar(255),
        next_sibling varchar(255),
        parent varchar(255),
        target varchar(255),
        score_id int4 not null,
        primary key (id)
    );

    alter table score 
       add constraint UK3ux9w0rpfpqrljat6wimun1fr unique (score_name);

    alter table accidental 
       add constraint FKf1brlbu5kohjtiexhuaj1uv1j 
       foreign key (level_display_id) 
       references level_display;

    alter table accidental 
       add constraint FKhvmx4yrlfgkjklrhf79jpnfox 
       foreign key (print_style_id) 
       references print_style;

    alter table accord 
       add constraint FKmallx8sq3vhlhproso60l144t 
       foreign key (tuning_id) 
       references tuning;

    alter table accord 
       add constraint FKtr8qmjwj8ddfyk2gi4wmhlm5a 
       foreign key (scordatura_id) 
       references direction_type;

    alter table articulation 
       add constraint FKb9visq0pvneglobry068cm9vu 
       foreign key (print_style_id) 
       references print_style;

    alter table articulation 
       add constraint FKfc1snbijvkog2rhbpw1on55g5 
       foreign key (placement_id) 
       references placement;

    alter table articulation 
       add constraint FK30m8vy3kinx56q1w2xbmp3bty 
       foreign key (line_id) 
       references line;

    alter table articulation 
       add constraint FKr6q7jr7cjrtg1eunx02nu9kre 
       foreign key (placement_text_id) 
       references placement_text;

    alter table articulation 
       add constraint FK1emxxnei3prqc823q15gt5ccp 
       foreign key (articulations_id) 
       references notation;

    alter table bass 
       add constraint FK89b8ei38psnad41dlrveugi8i 
       foreign key (bass_alter_id) 
       references bass_alter;

    alter table bass 
       add constraint FKatrignlphdp7f28hwjriexyo0 
       foreign key (bass_step_id) 
       references bass_step;

    alter table bass_alter 
       add constraint FKpp4rcwtmq1wrlkx5tfk3vwu8t 
       foreign key (print_style_id) 
       references print_style;

    alter table bass_step 
       add constraint FKsiahssnx0jfnglcjp1yl7q66c 
       foreign key (print_style_id) 
       references print_style;

    alter table beam 
       add constraint FKbirfijk9ipkvby7y04lbehl9i 
       foreign key (note_id) 
       references music_data;

    alter table clef 
       add constraint FK641n5bcx7pl7ts54jp85xym4b 
       foreign key (print_style_id) 
       references print_style;

    alter table clef 
       add constraint FKi3ndwd0j6jwnc9b4bs9qrhra8 
       foreign key (attributes_id) 
       references music_data;

    alter table credit 
       add constraint FKqt3t8jsqw9f8epk2mg3cbvogs 
       foreign key (score_header_id) 
       references score_header;

    alter table credit_display 
       add constraint FKfmotqq7ogiktuy0bgq1y5c2wt 
       foreign key (formatted_text_id) 
       references formatted_text;

    alter table credit_display 
       add constraint FKmglmu9us8bs64gvpcg1o713v1 
       foreign key (image_id) 
       references direction_type;

    alter table credit_display 
       add constraint FKl8qfo2ls4vq6ydjvqm6gbsexk 
       foreign key (credit_id) 
       references credit;

    alter table credit_type 
       add constraint FKrnby6jnu0wbl6k0j5ath1d22v 
       foreign key (credit_id) 
       references credit;

    alter table defaults 
       add constraint FKhmw7gcaplpxft7cdmaaxq2uhj 
       foreign key (appearance_id) 
       references appearance;

    alter table defaults 
       add constraint FKnk0e1h034e5hlj8uhgig5g10a 
       foreign key (layout_id) 
       references layout;

    alter table defaults 
       add constraint FK4n3eyndu2jmqx38rt96qmq5c1 
       foreign key (music_font_id) 
       references font;

    alter table defaults 
       add constraint FKpm5m8ghf3bwgx0wwrbgh5r0qq 
       foreign key (defaults_id) 
       references scaling;

    alter table defaults 
       add constraint FKj7vt9gdahpixshdx5ca37lw9b 
       foreign key (word_font_id) 
       references font;

    alter table degree 
       add constraint FKmt6nydutaow0199jjng5pv50i 
       foreign key (degree_alter_id) 
       references degree_alter;

    alter table degree 
       add constraint FK374vyyx5wb58it8ylocotxdo5 
       foreign key (degree_type_id) 
       references degree_type;

    alter table degree 
       add constraint FKgsps4nhla3ah77pfpsm32is8e 
       foreign key (degree_value_id) 
       references degree_value;

    alter table degree 
       add constraint FKiuti54o5d6w8ryt3sh941yym5 
       foreign key (harmony_chord_id) 
       references harmony_chord;

    alter table degree_alter 
       add constraint FKjn6riw3p0qfmbhjsxr4rjsn92 
       foreign key (print_style_id) 
       references print_style;

    alter table degree_type 
       add constraint FK9cvlxqwv12nortu2rhfsgefmp 
       foreign key (print_style_id) 
       references print_style;

    alter table degree_value 
       add constraint FKpteiuibun6oegnhwkc6bab13s 
       foreign key (print_style_id) 
       references print_style;

    alter table direction_type 
       add constraint FKg4bem6dw87egwlbf56tdtr3ae 
       foreign key (direction_type_list_id) 
       references direction_type_list;

    alter table direction_type 
       add constraint FKdh2w4ljp8adbmh0wamigdoik5 
       foreign key (print_style_align_id) 
       references print_style_align;

    alter table direction_type 
       add constraint FKq9gsdvosrioo2w6yl0374wsps 
       foreign key (fomatted_text_id) 
       references formatted_text;

    alter table direction_type 
       add constraint FKgxses1obcssr9l23473nifsgy 
       foreign key (dashed_formatting_id) 
       references dashed_formatting;

    alter table direction_type 
       add constraint FKn5f9n6k1d27reulja1lsk0tvj 
       foreign key (position_id) 
       references position;

    alter table direction_type 
       add constraint FK5rxl70mdfr2xjhjnvya78c4s9 
       foreign key (text_decoration_id) 
       references text_decoration;

    alter table direction_type 
       add constraint FK3gxkoia4ng1lhvkb0y8n69afa 
       foreign key (print_style_id) 
       references print_style;

    alter table direction_type 
       add constraint FKh064c4w60nigcc34qthbh5c33 
       foreign key (beat_unit_1_id) 
       references beat_unit;

    alter table direction_type 
       add constraint FKgxbebd1i9oygindubiojvhl4i 
       foreign key (beat_unit_2_id) 
       references beat_unit;

    alter table direction_type 
       add constraint FK8cyqpdh5f7xrf4bhnos93eee4 
       foreign key (per_minute_id) 
       references per_minute;

    alter table direction_type_list 
       add constraint FKblbd4ond4mhyu4o5cd0blqrsm 
       foreign key (direction_id) 
       references music_data;

    alter table directive 
       add constraint FKk8ns3fcpr5d516lnprp9o06hd 
       foreign key (print_style_id) 
       references print_style;

    alter table directive 
       add constraint FK7ksvq9gvmn531kkcf9yodu3be 
       foreign key (attributes_id) 
       references music_data;

    alter table distance 
       add constraint FKh7upf2u0uver0s5p0wbtug9y 
       foreign key (appearance_id) 
       references appearance;

    alter table dot 
       add constraint FKhpdcxbtq5p56wcq1fvr3udxes 
       foreign key (placement_id) 
       references placement;

    alter table dot 
       add constraint FKinehtcky1m5rv5wexupjmixs 
       foreign key (note_id) 
       references music_data;

    alter table dynamics_marking 
       add constraint FKpg0vojk8lfwqhy6wpuo8xy36a 
       foreign key (dynamics_id) 
       references direction_type;

    alter table editorial 
       add constraint FK80idprxv3qcwtyg2yvegdreea 
       foreign key (footnote_id) 
       references formatted_text;

    alter table editorial 
       add constraint FKtarh2ma91jy07go32cnwywx16 
       foreign key (level_id) 
       references level;

    alter table editorial_voice 
       add constraint FK1ustnq8hd3952fjhxmrwjoix0 
       foreign key (footnote_id) 
       references formatted_text;

    alter table editorial_voice 
       add constraint FK7oq2hhyjcy1qfe6n0pe9avmkd 
       foreign key (level_id) 
       references level;

    alter table editorial_voice_direction 
       add constraint FKcm3f38qna2foj87kxxm2y0icg 
       foreign key (footnote_id) 
       references formatted_text;

    alter table editorial_voice_direction 
       add constraint FK51karyxh4hn023p6419t60g0n 
       foreign key (level_id) 
       references level;

    alter table encoding 
       add constraint FK8l5bbf29jxhcjw32xffoh6lqe 
       foreign key (encoder_id) 
       references typed_text;

    alter table encoding 
       add constraint FKj8g0yqb3iyn82s4pfgrlolqvl 
       foreign key (identification_id) 
       references identification;

    alter table ending 
       add constraint FKkonmj86oig30fhaao3drtsm7u 
       foreign key (print_style_id) 
       references print_style;

    alter table feature 
       add constraint FKmul6tobnvi4eghf76th7h3mdq 
       foreign key (grouping_id) 
       references music_data;

    alter table figure 
       add constraint FK24qvk8c7i5mwab6x26eqsh4a7 
       foreign key (extend_id) 
       references lyric_item;

    alter table figure 
       add constraint FKryuf4787pawpnahh3y02ux696 
       foreign key (figure_number_id) 
       references style_text;

    alter table figure 
       add constraint FKg1f1l3hcucqy1tq0rnugwkqo4 
       foreign key (prefix_id) 
       references style_text;

    alter table figure 
       add constraint FKrn0kj7lltx4ttdghm8l0tyrtd 
       foreign key (suffix_id) 
       references style_text;

    alter table figure 
       add constraint FK7nar4w4ybmv0kjnvi9xc78r36 
       foreign key (figured_bass_id) 
       references music_data;

    alter table font 
       add constraint FKcm9ljstral5kgbygy0ljqq8sk 
       foreign key (font_size_id) 
       references font_size;

    alter table formatted_text 
       add constraint FKno6nplbyevs8p1csp145pfcby 
       foreign key (text_formatting_id) 
       references text_formatting;

    alter table frame 
       add constraint FKbu912vqnib5jvntkcd275bsb7 
       foreign key (first_fret_id) 
       references first_fret;

    alter table frame 
       add constraint FKch3k2c0mdjgk7g8ce25n2o1xw 
       foreign key (position_id) 
       references position;

    alter table frame_note 
       add constraint FKc2bu06jqrnpjaks3lpxhwfkwf 
       foreign key (barre_id) 
       references barre;

    alter table frame_note 
       add constraint FK76ygr56dhil0h379d5j4psuvm 
       foreign key (fingering_id) 
       references technical;

    alter table frame_note 
       add constraint FKicau0l2kgjcx89dq5xa5n7ntw 
       foreign key (fret_id) 
       references technical;

    alter table frame_note 
       add constraint FKa9es26wi46lj5apqkskagihns 
       foreign key (string_id) 
       references technical;

    alter table frame_note 
       add constraint FKh021x1kxvt915g5j9n4odf6ew 
       foreign key (frame_id) 
       references frame;

    alter table full_note 
       add constraint FKlbq0kh1gpwj47gypadhfgdg7m 
       foreign key (full_note_type_id) 
       references full_note_type;

    alter table group_name 
       add constraint FKgklulvowae4d6pkxq8ia8d6dx 
       foreign key (print_style_id) 
       references print_style;

    alter table group_symbol 
       add constraint FKjf3mb2qdawomkmetttl00n0ws 
       foreign key (position_id) 
       references position;

    alter table harmony_chord 
       add constraint FK29a3948b7puxtfjiwexeku67t 
       foreign key (bass_id) 
       references bass;

    alter table harmony_chord 
       add constraint FKrf7vp5xacsmtgo6l21hl76alu 
       foreign key (inversion_id) 
       references inversion;

    alter table harmony_chord 
       add constraint FKh2gdlbcwj11qkflxksho57uty 
       foreign key (kind_id) 
       references kind;

    alter table harmony_chord 
       add constraint FKj0vumsal06gcidv6av6k8p9tk 
       foreign key (root_alter_id) 
       references root_alter;

    alter table harmony_chord 
       add constraint FKdid62dpfcwqx730949k9cmw3m 
       foreign key (root_step_id) 
       references root_step;

    alter table harmony_chord 
       add constraint FKmhu4kvcxq786un0fip9976x7p 
       foreign key (function_id) 
       references style_text;

    alter table harmony_chord 
       add constraint FK19k6wkl43vtpscei2pbku2wdc 
       foreign key (harmony_id) 
       references music_data;

    alter table identification 
       add constraint FK2fo2yc59a55etdbajev56nak7 
       foreign key (miscellaneous_id) 
       references miscellaneous;

    alter table interchangeable 
       add constraint FKavstr6ajrdw305ahlh9tivfnn 
       foreign key (time_signature_id) 
       references time;

    alter table inversion 
       add constraint FKms7b0og13lb4di6abm1dvg5nx 
       foreign key (print_style_id) 
       references print_style;

    alter table key_octave 
       add constraint FKkt0hsurbjdyb1x6jjdfj3j9h6 
       foreign key (key_id) 
       references key_signature;

    alter table key_signature 
       add constraint FKd7tumy231x2kcgyodblg3a6du 
       foreign key (print_style_id) 
       references print_style;

    alter table key_signature 
       add constraint FK82qm60fbbgff6m3dn8txr0dko 
       foreign key (cancel_id) 
       references cancel;

    alter table key_signature 
       add constraint FKddfd76kt7qik3tiygvjx249wx 
       foreign key (attributes_id) 
       references music_data;

    alter table kind 
       add constraint FKm66mw2xl9mqj912bx7q3xhjtf 
       foreign key (print_style_id) 
       references print_style;

    alter table layout 
       add constraint FK552vkgbeaq1y95tslmngsb7yi 
       foreign key (page_layout_id) 
       references page_layout;

    alter table layout 
       add constraint FK7smcened627hb3fwcsewg4gyv 
       foreign key (systemm_layout_id) 
       references system_layout;

    alter table level 
       add constraint FKcsxqav3kw8vepoja0kkjc93ul 
       foreign key (level_display_id) 
       references level_display;

    alter table line 
       add constraint FKom9rcpsr4cn14tt4k2jarkhg9 
       foreign key (dashed_formatting_id) 
       references dashed_formatting;

    alter table line 
       add constraint FKeuni9nyggd7jbfrr98vkssogu 
       foreign key (print_style_id) 
       references print_style;

    alter table line_width 
       add constraint FKolk6o737efsh34fe1lbl3646t 
       foreign key (appearance_id) 
       references appearance;

    alter table lyric 
       add constraint FKjyeuo5oulnpb4hiid8deb75ot 
       foreign key (editorial_id) 
       references editorial;

    alter table lyric 
       add constraint FKog91nc853a53gcxgbvoqaji70 
       foreign key (lyric_item_id) 
       references lyric_item;

    alter table lyric 
       add constraint FKpasbsn08ubag7mv6cakyq1k2k 
       foreign key (position_id) 
       references position;

    alter table lyric 
       add constraint FK4pb1ar6j9e34u8qgcjq5nsroq 
       foreign key (note_id) 
       references music_data;

    alter table lyric_font 
       add constraint FKjbwccxkqmlgtjg5eyif0rwgpk 
       foreign key (font_id) 
       references font;

    alter table lyric_font 
       add constraint FKiyxejv2mkqn3755yg4iedo1j0 
       foreign key (defaults_id) 
       references defaults;

    alter table lyric_item 
       add constraint FK9a3ar7es2i15r02cc0tgagrva 
       foreign key (print_style_id) 
       references print_style;

    alter table lyric_item 
       add constraint FKbjmewleq4j31k1dtppbtcp4dh 
       foreign key (extend_id) 
       references lyric_item;

    alter table lyric_language 
       add constraint FKn8jv5f6ji369lamut0desxu0k 
       foreign key (defaults_id) 
       references defaults;

    alter table lyric_syllable 
       add constraint FKnbj8a56ent8xvel21vse6qou0 
       foreign key (lyric_elision_id) 
       references text_font_color;

    alter table lyric_syllable 
       add constraint FKkqawno7vu5e2hpebgmkvbwme3 
       foreign key (text_data_id) 
       references text_data;

    alter table lyric_syllable 
       add constraint FK25xj1xbkynu8c87129s2qodt7 
       foreign key (lyric_text_id) 
       references lyric_item;

    alter table measure 
       add constraint FK7okawlbg5nyqon30ll7uavx28 
       foreign key (part_id) 
       references part;

    alter table measure_style 
       add constraint FKan3i4e80tqacry684lwp4fvhf 
       foreign key (font_id) 
       references font;

    alter table measure_style 
       add constraint FKbj6cxo895qbe3hj8h5xwls7t 
       foreign key (slash_group_id) 
       references slash_group;

    alter table measure_style 
       add constraint FKc1nsoxwrw5l5gxv6e0c6oqwob 
       foreign key (attributes_id) 
       references music_data;

    alter table metronome_beam 
       add constraint FK7da67iuei7y91g3n7nkd97q9 
       foreign key (metronome_note_id) 
       references metronome_note;

    alter table metronome_note 
       add constraint FK40x0x8qv6ynww6qot6dhgss 
       foreign key (metronome_tuplet_id) 
       references metronome_tuplet;

    alter table metronome_note 
       add constraint FKr0rle1jd7659157cwj1vqo8yv 
       foreign key (note_metronome_2_id) 
       references direction_type;

    alter table metronome_note 
       add constraint FKhngg4ep884s7ppwy919g7wwjs 
       foreign key (note_metronome_1_id) 
       references direction_type;

    alter table metronome_tuplet 
       add constraint FK6qhojnpcqb3h0jl7knwj71908 
       foreign key (time_modification_id) 
       references time_modification;

    alter table miscellaneous_field 
       add constraint FK3rpl04f3aqqmuymkscglp4yg0 
       foreign key (miscellaneous_id) 
       references miscellaneous;

    alter table music_data 
       add constraint FKtgso1241d1sdi5q4weg4982iw 
       foreign key (measure_id) 
       references measure;

    alter table music_data 
       add constraint FKi1o1cwuyrqsq5f1s3apa4hf4y 
       foreign key (editorial_id) 
       references editorial;

    alter table music_data 
       add constraint FKsfyj9m6igtt1bktnjrb9e7h63 
       foreign key (offset_id) 
       references direction_offset;

    alter table music_data 
       add constraint FK1glg24qa65ycutycdd0skqrkb 
       foreign key (print_style_id) 
       references print_style;

    alter table music_data 
       add constraint FKevg7nxdah1nb082n9dl1bivw2 
       foreign key (printout_id) 
       references printout;

    alter table music_data 
       add constraint FK7m139gbx76gy3pnnnqkq5saak 
       foreign key (part_symbol_id) 
       references part_symbol;

    alter table music_data 
       add constraint FKr6r8ce7ggimnhk0slqpnec9j5 
       foreign key (frame_id) 
       references frame;

    alter table music_data 
       add constraint FK90kvf49b2hf9ohcxlbmjo9tcj 
       foreign key (bar_style_id) 
       references bar_style_color;

    alter table music_data 
       add constraint FKq9plxquyjpdjmgxlfrxtjajub 
       foreign key (coda_print_id) 
       references print_style_align;

    alter table music_data 
       add constraint FK6nxn99o8j8pik2kry92d4xs60 
       foreign key (ending_id) 
       references ending;

    alter table music_data 
       add constraint FKn81inqqf1uecp9lrqpoece8pb 
       foreign key (repeat_id) 
       references barline_repeat;

    alter table music_data 
       add constraint FKhh4obibkl94v8mlpgfh81ts4k 
       foreign key (segno_print_id) 
       references print_style_align;

    alter table music_data 
       add constraint FKig1bj9flp7ebecbgfe2jq8f9c 
       foreign key (wavy_line_id) 
       references ornament;

    alter table music_data 
       add constraint FKqlptld1w9lk384q8o76ocxyjc 
       foreign key (layout_id) 
       references layout;

    alter table music_data 
       add constraint FKa1ma35y52cpfkiqv11j5fgi8p 
       foreign key (measure_layout_id) 
       references measure_layout;

    alter table music_data 
       add constraint FKb1tvu3enlmm3de7txjuvnu3jf 
       foreign key (part_abbreviation_display_id) 
       references name_display;

    alter table music_data 
       add constraint FK5ukkyfjov39beuik27uge8dex 
       foreign key (part_name_display_id) 
       references name_display;

    alter table music_data 
       add constraint FKntp0ktd4hfsrs8912th1qhefu 
       foreign key (print_style_align_id) 
       references print_style_align;

    alter table music_data 
       add constraint FKowu22dl3y19shmrdmjxok999l 
       foreign key (credit_display_id) 
       references credit_display;

    alter table music_data 
       add constraint FKs9hdr0f6wuyl1vc4o87odv54w 
       foreign key (element_position_id) 
       references element_position;

    alter table music_data 
       add constraint FKhw4wtp4400eh1bn1dwsxce4wm 
       foreign key (editorial_voice_id) 
       references editorial_voice;

    alter table music_data 
       add constraint FK8l5ctbd1hibellcrcge7oob41 
       foreign key (link_attributes_id) 
       references link_attributes;

    alter table music_data 
       add constraint FKl0qlgpfjb0jha1nexwaps1q79 
       foreign key (position_id) 
       references position;

    alter table music_data 
       add constraint FKrbocaap3axrypmkiowm32k1pu 
       foreign key (accidental_id) 
       references accidental;

    alter table music_data 
       add constraint FKgpicksq8yhmln32p4k3ujfaxm 
       foreign key (font_id) 
       references font;

    alter table music_data 
       add constraint FKbr7vqgo6v4vt89fc0g38m427r 
       foreign key (full_note_id) 
       references full_note;

    alter table music_data 
       add constraint FKi7rv04dyy4fqqa33kpdlniiet 
       foreign key (grace_id) 
       references grace;

    alter table music_data 
       add constraint FKlgqhcv9mhmdfto1nc6930m13x 
       foreign key (notehead_id) 
       references notehead;

    alter table music_data 
       add constraint FKevjnhly5dgfsfx45hltdyy60w 
       foreign key (notehead_text_id) 
       references notehead_text;

    alter table music_data 
       add constraint FKqx0mbshxroldy3h0rhnjuen96 
       foreign key (play_id) 
       references play;

    alter table music_data 
       add constraint FK26il8bcl7nar7x2q2su94rxil 
       foreign key (stem_id) 
       references stem;

    alter table music_data 
       add constraint FKri8bv21rdi21mgj1mbr5rfoi6 
       foreign key (time_modification_id) 
       references time_modification;

    alter table music_data 
       add constraint FKuctrlh56grlsxssco02op0ov 
       foreign key (type_id) 
       references note_type;

    alter table music_data 
       add constraint FK2i08pgrm0rx7tou1rl64m86h9 
       foreign key (editorial_voice_direction_id) 
       references editorial_voice_direction;

    alter table music_data 
       add constraint FKex8und0d9f4odkobxomoq7ia4 
       foreign key (sound_id) 
       references music_data;

    alter table non_traditional_key_type 
       add constraint FKq72vcji3in5uw4f22rylm92y 
       foreign key (non_traditional_key_id) 
       references key_signature;

    alter table notation 
       add constraint FK5r5gx0kjtp620dora5pb9b4bf 
       foreign key (notations_id) 
       references notations;

    alter table notation 
       add constraint FKhw3gdh6pej7kc22xvudakk9xf 
       foreign key (position_id) 
       references position;

    alter table notation 
       add constraint FK36e6fcoa8ttpfy0b4vq2vlqoi 
       foreign key (print_style_id) 
       references print_style;

    alter table notation 
       add constraint FKmikst8yb146tli0w558f0goly 
       foreign key (dashed_formatting_id) 
       references dashed_formatting;

    alter table notation 
       add constraint FKowp9wk2tvekwg5l3i54jpjti4 
       foreign key (bezier_id) 
       references bezier;

    alter table notation 
       add constraint FKge2o4tclmalr1xjsh6nlkbh0k 
       foreign key (bend_sound_id) 
       references bend_sound;

    alter table notation 
       add constraint FKjeqwla80mk04atqle9daqb3ov 
       foreign key (barline_id) 
       references music_data;

    alter table notation 
       add constraint FKql0hwi2lhw9s6bxg21efkac8r 
       foreign key (tuplet_actual_id) 
       references tuplet_portion;

    alter table notation 
       add constraint FKkrp8lq0ufd9vdhn3objqvr2e1 
       foreign key (tuplet_normal_id) 
       references tuplet_portion;

    alter table notation 
       add constraint FK853o1h22svc12h2hujyq9vsk4 
       foreign key (dynamics_id) 
       references direction_type;

    alter table notations 
       add constraint FKg6a7ep34rh5lufsyo8ajv34nk 
       foreign key (editorial_id) 
       references editorial;

    alter table notations 
       add constraint FKdpmega5xo8iotvissrrj41pcj 
       foreign key (note_id) 
       references music_data;

    alter table note_size 
       add constraint FKm5i756uip4c7k0iy0f4djb0u6 
       foreign key (appearance_id) 
       references appearance;

    alter table notehead 
       add constraint FKpkifmgv81yf7xav93tpvccvya 
       foreign key (font_id) 
       references font;

    alter table ornament 
       add constraint FKnmqa6fuo6igfi4jv79gh6dwxs 
       foreign key (ornaments_id) 
       references notation;

    alter table ornament 
       add constraint FKoy2jywq7553xrg55v0fybhnsr 
       foreign key (placement_text_id) 
       references placement_text;

    alter table ornament 
       add constraint FK11qmo7gioqgwq94c8876lgajb 
       foreign key (print_style_id) 
       references print_style;

    alter table ornament 
       add constraint FKicpik38a8cvj0yg8ke41uth0n 
       foreign key (trill_sound_id) 
       references trill_sound;

    alter table ornament 
       add constraint FKltibfgn7t6c6qp46ro378p7o1 
       foreign key (position_id) 
       references position;

    alter table ornament 
       add constraint FK76kucutbhy17dgd30vtfh7ase 
       foreign key (placement_id) 
       references placement;

    alter table ornament_accidental 
       add constraint FKqtdjoc60gbgmlnwclvq7buffj 
       foreign key (accidental_mark_id) 
       references notation;

    alter table ornament_accidental 
       add constraint FKigbvliujgu40hsgvtaa8cxelg 
       foreign key (ornaments_id) 
       references notation;

    alter table other_appearance 
       add constraint FK29ytkk345jy90rk52hlodv2ux 
       foreign key (appearance_id) 
       references appearance;

    alter table page_margins 
       add constraint FK6lm9a1utyrw4gd9snues86pr4 
       foreign key (margins_id) 
       references margins;

    alter table page_margins 
       add constraint FKk1q1gu5u33a4xiasb9las6s1v 
       foreign key (page_layout_id) 
       references page_layout;

    alter table part 
       add constraint FKqqcwcg3r43dy3uad73jatgtws 
       foreign key (score_id) 
       references score;

    alter table part_item 
       add constraint FKalucdemf7gtwxkabxehmxcy3d 
       foreign key (editorial_id) 
       references editorial;

    alter table part_item 
       add constraint FK9lw931tmgclaam3v9gkwt25fi 
       foreign key (group_abbreviation_id) 
       references group_name;

    alter table part_item 
       add constraint FKbovai8x25y2ps84n9lrlcd8nl 
       foreign key (group_abbreviation_display_id) 
       references name_display;

    alter table part_item 
       add constraint FKifg0mcwm417gwjf0i8br3j4x5 
       foreign key (group_barline_id) 
       references group_barline;

    alter table part_item 
       add constraint FK9768u4iaw2m3k87qrxilq31xh 
       foreign key (group_name_id) 
       references group_name;

    alter table part_item 
       add constraint FKcg98fbwsvme2njeruoj528l9d 
       foreign key (group_name_display_id) 
       references name_display;

    alter table part_item 
       add constraint FK9ny4awt5k7hbnyk2r8n0gmns7 
       foreign key (group_symbol_id) 
       references group_symbol;

    alter table part_item 
       add constraint FK9saxlsbtludhbx3dfne85m8tc 
       foreign key (identification_id) 
       references identification;

    alter table part_item 
       add constraint FKeecgjcbomb483vemger9sy0xn 
       foreign key (part_abbreviation_id) 
       references part_name;

    alter table part_item 
       add constraint FK9x3js1vy2vxckjb2p6kh6vw7k 
       foreign key (part_abbreviation_display_id) 
       references name_display;

    alter table part_item 
       add constraint FKb8wcr8kpm3vow4hciwepak1b0 
       foreign key (part_name_id) 
       references part_name;

    alter table part_item 
       add constraint FKwhb7j9b98a73gwwjir309qk4 
       foreign key (part_name_display_id) 
       references name_display;

    alter table part_item 
       add constraint FK2i5yk2l02xr0le5h3biidnk29 
       foreign key (part_list_id) 
       references part_list;

    alter table part_name 
       add constraint FKk9w82qcy4l6bcwpbwohkhmtg5 
       foreign key (part_name_print_style_id) 
       references print_style;

    alter table part_symbol 
       add constraint FK28ea4fghgnp1wxdp88ro9ufd0 
       foreign key (position_id) 
       references position;

    alter table pedal_tuning 
       add constraint FKkvm1vni5oi78rbwmadfpcymb8 
       foreign key (harp_pedals_id) 
       references direction_type;

    alter table per_minute 
       add constraint FKtmhu8smc4fg2l2wvb2d6p3q1s 
       foreign key (font_id) 
       references font;

    alter table placement 
       add constraint FK4ns907staxuc3otbrofrwj1xv 
       foreign key (print_style_id) 
       references print_style;

    alter table placement_text 
       add constraint FKqd2x7uux6uwq2dsfrt4tvtt4l 
       foreign key (print_style_id) 
       references print_style;

    alter table play_type 
       add constraint FKlqyvck4o1sx2npdvpvi2cr0iq 
       foreign key (play_id) 
       references play;

    alter table print_object_style_align 
       add constraint FKemcmcj4t3ac5godq388m91gl6 
       foreign key (print_style_align_id) 
       references print_style_align;

    alter table print_style 
       add constraint FKg994nyn3c4wd9xn00qm7wdr7q 
       foreign key (font_id) 
       references font;

    alter table print_style 
       add constraint FK74tp99ldhfbp6b1uahk55tg2c 
       foreign key (position_id) 
       references position;

    alter table print_style_align 
       add constraint FK88wnduf1il60ole2a5agiu91t 
       foreign key (print_style_id) 
       references print_style;

    alter table root_alter 
       add constraint FKma2bv9wu07wrfouuoeft7tmul 
       foreign key (print_style_id) 
       references print_style;

    alter table root_step 
       add constraint FK9yripfxgsgrtfm27br0aonfc7 
       foreign key (print_style_id) 
       references print_style;

    alter table score 
       add constraint FKibymmlpensin2h24pto4i98l0 
       foreign key (score_header_id) 
       references score_header;

    alter table score_header 
       add constraint FKgo0fi29rwnnmp0dg6nim7ed2s 
       foreign key (defaults_id) 
       references defaults;

    alter table score_header 
       add constraint FKoe9ya6l782yx8djknyn86th2i 
       foreign key (identification_id) 
       references identification;

    alter table score_header 
       add constraint FK6va9w87bq4hdy87wsytra1wfd 
       foreign key (part_list_id) 
       references part_list;

    alter table score_header 
       add constraint FK5k1ou1a2c23hh1u41odt6nsj4 
       foreign key (work_id) 
       references work;

    alter table score_instrument 
       add constraint FK4y35rg4o1u4687sj7c8e5hx96 
       foreign key (instrument_type_id) 
       references instrument_type;

    alter table score_instrument 
       add constraint FK6onkgwv28tgsh2uo01imuawn2 
       foreign key (virtual_instrument_id) 
       references virtual_instrument;

    alter table score_instrument 
       add constraint FKcbyc4ag5k50ixryb3cttfavkn 
       foreign key (score_part_id) 
       references part_item;

    alter table score_midi 
       add constraint FKcs9atgsafal12ewyjbh65wdx0 
       foreign key (midi_device_id) 
       references midi_device;

    alter table score_midi 
       add constraint FK7nfn04do2a3oyf278dgcorfhi 
       foreign key (midi_instrument_id) 
       references midi_instrument;

    alter table score_midi 
       add constraint FK65xe1ho5kaux74963negok8vs 
       foreign key (score_part_id) 
       references part_item;

    alter table score_part_group 
       add constraint FKq69u1jydxash60pa1hcgvhtnr 
       foreign key (score_part_id) 
       references part_item;

    alter table sound_midi 
       add constraint FKr95ayv8tlse4jeyg0xaidll9q 
       foreign key (midi_device_id) 
       references midi_device;

    alter table sound_midi 
       add constraint FK1lybxj91pl9dd7esj72dvmubu 
       foreign key (midi_instrument_id) 
       references midi_instrument;

    alter table sound_midi 
       add constraint FKbibsj8iumy2p9aghful30nyoa 
       foreign key (play_id) 
       references play;

    alter table sound_midi 
       add constraint FK2v6h4if41nww1w81u0xys8nh5 
       foreign key (sound_id) 
       references music_data;

    alter table staff_details 
       add constraint FKblmua0qf8os1g3guaj5k9v0pn 
       foreign key (attributes_id) 
       references music_data;

    alter table staff_layout 
       add constraint FK7wvfkni379jy12uk833tnd1wm 
       foreign key (layout_id) 
       references layout;

    alter table staff_tuning 
       add constraint FKbayw4xdr3fxlk6qhovm3jp2b 
       foreign key (tuning_id) 
       references tuning;

    alter table staff_tuning 
       add constraint FKeluy2c31cvumfm0dd9tfmq70u 
       foreign key (staff_details_id) 
       references staff_details;

    alter table stem 
       add constraint FKe4y9aepsj1abjlglped2fepyn 
       foreign key (position_id) 
       references position;

    alter table style_text 
       add constraint FKkrfn8pxu2pgqiudyof137yedr 
       foreign key (print_style_id) 
       references print_style;

    alter table system_dividers 
       add constraint FKs4snu7ps4ci8gc5xqmgwh95je 
       foreign key (left_divider_id) 
       references print_object_style_align;

    alter table system_dividers 
       add constraint FKkg34yq81yeow6wevtew79gse9 
       foreign key (right_divider_id) 
       references print_object_style_align;

    alter table system_layout 
       add constraint FKoum2iw0o827holjk4avmetxpt 
       foreign key (system_dividers_id) 
       references system_dividers;

    alter table technical 
       add constraint FKk1p1j3espd2ej3cfk81elx7qd 
       foreign key (technicals_id) 
       references notation;

    alter table technical 
       add constraint FK8weangm16x6rrxt3327f9ehny 
       foreign key (print_style_id) 
       references print_style;

    alter table technical 
       add constraint FKtrnbihlvpf714x3m6gegvedkd 
       foreign key (placement_id) 
       references placement;

    alter table technical 
       add constraint FK8x6phe95o7ktbmu6wfkp3van0 
       foreign key (font_id) 
       references font;

    alter table technical 
       add constraint FKnycbnsdcafannuju6bri39xgm 
       foreign key (placement_text_id) 
       references placement_text;

    alter table technical 
       add constraint FKk2qoca6rp76q8da4xyyk8mpug 
       foreign key (bend_sound_id) 
       references bend_sound;

    alter table technical 
       add constraint FKm76u1honwnfwuj1u3tx82fooj 
       foreign key (with_bar_id) 
       references placement_text;

    alter table text_data 
       add constraint FKe2m2pkl4m1lac5b4ufglvllb2 
       foreign key (font_id) 
       references font;

    alter table text_data 
       add constraint FK32ns7tu4ejkkyvir13v9n9m7d 
       foreign key (text_decoration_id) 
       references text_decoration;

    alter table text_display 
       add constraint FKewbqxuv4t3j4je18bc13ni6je 
       foreign key (name_display_id) 
       references name_display;

    alter table text_display 
       add constraint FKgcm9yg2bcd4ubd3a5dyxef50v 
       foreign key (notehead_text_id) 
       references notehead_text;

    alter table text_display 
       add constraint FKa9mybrb3rf4nona3w4a85kxxw 
       foreign key (text_formatting_id) 
       references text_formatting;

    alter table text_display 
       add constraint FKa83jcmmr893rnm7fu0m79hspa 
       foreign key (display_text_id) 
       references formatted_text;

    alter table text_font_color 
       add constraint FK9s816v01lg2rqcy1l4ad8pyk1 
       foreign key (font_id) 
       references font;

    alter table text_font_color 
       add constraint FK1x9nxgxcdytcwfy2yim5sl4rg 
       foreign key (text_decoration_id) 
       references text_decoration;

    alter table text_formatting 
       add constraint FKkf85m66nv1fx2xqcf28atirb5 
       foreign key (print_style_align_id) 
       references print_style_align;

    alter table text_formatting 
       add constraint FKhdstyqjscdf2v55ewtksqrtx8 
       foreign key (text_decoration_id) 
       references text_decoration;

    alter table tie 
       add constraint FKj5k6akoif382o12wegpt952g9 
       foreign key (note_id) 
       references music_data;

    alter table time 
       add constraint FKgcs6ifoohhwyyyb0ryyfucbkv 
       foreign key (print_style_align_id) 
       references print_style_align;

    alter table time 
       add constraint FK287pjbbbjekxggqk4c9n72oyr 
       foreign key (interchangeable_id) 
       references interchangeable;

    alter table time 
       add constraint FKkcgehm6pex2autb9d57hx7aqs 
       foreign key (attributes_id) 
       references music_data;

    alter table time_signature_type 
       add constraint FK6m54smq3gfdesiu0t32o6s4wh 
       foreign key (time_signature_id) 
       references time;

    alter table transpose 
       add constraint FKmxnumkuq4nsn7wpf9apuyftda 
       foreign key (attributes_id) 
       references music_data;

    alter table tuplet_dot 
       add constraint FKkyjpq6x9qk0gn8js5g6jbyur5 
       foreign key (font_id) 
       references font;

    alter table tuplet_dot 
       add constraint FK17shk9waf0d00k9f69xprvof8 
       foreign key (tuplet_portion_id) 
       references tuplet_portion;

    alter table tuplet_number 
       add constraint FK3m5v7rsxy5wifp2sxnkugtpp4 
       foreign key (font_id) 
       references font;

    alter table tuplet_portion 
       add constraint FK7vx5g52oawiuajlnkhv6atbyc 
       foreign key (tuplet_number_id) 
       references tuplet_number;

    alter table tuplet_portion 
       add constraint FKtwl390gk0ghoye5fu7tnmyef 
       foreign key (tuplet_type_id) 
       references tuplet_type;

    alter table tuplet_type 
       add constraint FK47yv2u1ovwoiuw4jwr9hxoevp 
       foreign key (font_id) 
       references font;

    alter table typed_text 
       add constraint FKkug2adi648rr488ym15018bg8 
       foreign key (creator_id) 
       references identification;

    alter table typed_text 
       add constraint FK4jv08cd41ebir0ykptwfjm4iq 
       foreign key (relation_id) 
       references identification;

    alter table typed_text 
       add constraint FKp1jefy6hfcnocp0p0j2ymgtbf 
       foreign key (rights_id) 
       references identification;

    alter table work 
       add constraint FK3tuolhyyg0q9pw6ca6iqyk0at 
       foreign key (opus_id) 
       references link_attributes;

    alter table xml_comment 
       add constraint FK3le0xsxpmaagtjiy1625diafq 
       foreign key (score_id) 
       references score;