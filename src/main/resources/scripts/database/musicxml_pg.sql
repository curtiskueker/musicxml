create sequence native start 1 increment 1;

    create table accidental (
       id int4 not null,
        accidental_type varchar(255),
        cautionary char(1),
        editorial char(1),
        smufl varchar(255),
        display_id int4,
        level_display_id int4,
        primary key (id)
    );

    create table accord (
       id int4 not null,
        string int4,
        tuning_id int4,
        scordatura_id int4 not null,
        primary key (id)
    );

    create table appearance (
       id int4 not null,
        primary key (id)
    );

    create table articulation (
       articulation_type varchar(31) not null,
        id int4 not null,
        breath_mark_value varchar(255),
        caesura_value varchar(255),
        type varchar(255),
        smufl varchar(255),
        value varchar(255),
        display_id int4,
        line_id int4,
        articulations_id int4 not null,
        primary key (id)
    );

    create table barline_repeat (
       id int4 not null,
        direction varchar(255),
        times int4,
        winged varchar(255),
        primary key (id)
    );

    create table barre (
       id int4 not null,
        type varchar(255),
        display_id int4,
        primary key (id)
    );

    create table bass (
       id int4 not null,
        bass_alter_id int4,
        bass_step_id int4,
        primary key (id)
    );

    create table bass_alter (
       id int4 not null,
        location varchar(255),
        print_object char(1),
        semitones numeric(12, 4),
        display_id int4,
        primary key (id)
    );

    create table bass_step (
       id int4 not null,
        step varchar(255),
        text varchar(255),
        display_id int4,
        primary key (id)
    );

    create table beam (
       id int4 not null,
        element_id varchar(255),
        fan varchar(255),
        beam_number int4,
        repeater char(1),
        type varchar(255),
        display_id int4,
        note_id int4 not null,
        primary key (id)
    );

    create table beat_unit_tied (
       id int4 not null,
        beat_unit_tied_parent_id int4,
        beat_unit_parent_id int4 not null,
        primary key (id)
    );

    create table bend_sound (
       id int4 not null,
        accelerate char(1),
        beats numeric(12, 4),
        first_beat numeric(12, 4),
        last_beat numeric(12, 4),
        primary key (id)
    );

    create table bezier (
       id int4 not null,
        bezier_offset numeric(12, 4),
        bezier_offset2 numeric(12, 4),
        bezier_x numeric(12, 4),
        bezier_x2 numeric(12, 4),
        bezier_y numeric(12, 4),
        bezier_y2 numeric(12, 4),
        primary key (id)
    );

    create table cancel (
       id int4 not null,
        fifths int4,
        location varchar(255),
        primary key (id)
    );

    create table clef (
       id int4 not null,
        element_id varchar(255),
        additional char(1),
        after_barline char(1),
        clef_octave_change int4,
        line int4,
        clef_number int4,
        print_object char(1),
        sign varchar(255),
        symbol_size varchar(255),
        display_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table credit (
       id int4 not null,
        element_id varchar(255),
        page int4,
        score_header_id int4 not null,
        primary key (id)
    );

    create table credit_display (
       credit_display_type varchar(31) not null,
        id int4 not null,
        element_id varchar(255),
        display_id int4,
        text_format_id int4,
        image_id int4,
        credit_id int4 not null,
        primary key (id)
    );

    create table credit_type (
       id int4 not null,
        type varchar(255),
        credit_id int4 not null,
        primary key (id)
    );

    create table dashed_formatting (
       id int4 not null,
        dash_length numeric(12, 4),
        space_length numeric(12, 4),
        primary key (id)
    );

    create table defaults (
       id int4 not null,
        appearance_id int4,
        layout_id int4,
        music_font_id int4,
        defaults_id int4,
        word_font_id int4,
        primary key (id)
    );

    create table degree (
       id int4 not null,
        print_object char(1),
        degree_alter_id int4,
        degree_type_id int4,
        degree_value_id int4,
        harmony_chord_id int4 not null,
        primary key (id)
    );

    create table degree_alter (
       id int4 not null,
        plus_minus char(1),
        semitones numeric(12, 4),
        display_id int4,
        primary key (id)
    );

    create table degree_type (
       id int4 not null,
        text varchar(255),
        value varchar(255),
        display_id int4,
        primary key (id)
    );

    create table degree_value (
       id int4 not null,
        symbol varchar(255),
        text varchar(255),
        value int4,
        display_id int4,
        primary key (id)
    );

    create table direction_offset (
       id int4 not null,
        divisions numeric(12, 4),
        sound char(1),
        primary key (id)
    );

    create table direction_type (
       direction_type_type varchar(31) not null,
        id int4 not null,
        element_id varchar(255),
        enclosure varchar(255),
        direction_type varchar(255),
        accordion_high char(1),
        accordion_low char(1),
        accordion_middle int4,
        line_type varchar(255),
        niente char(1),
        direction_type_number int4,
        spread numeric(12, 4),
        smufl varchar(255),
        value varchar(255),
        abbreviated char(1),
        line char(1),
        pedal_number int4,
        pedal_type varchar(255),
        sign char(1),
        height numeric(12, 4),
        source varchar(255),
        width numeric(12, 4),
        beater_value varchar(255),
        tip varchar(255),
        justify varchar(255),
        parentheses char(1),
        metronome_arrows char(1),
        metronome_relation varchar(255),
        principal_voice varchar(255),
        symbol varchar(255),
        print_object char(1),
        stick_material varchar(255),
        stick_type varchar(255),
        end_length numeric(12, 4),
        line_end varchar(255),
        direction_type_size int4,
        staff_divide_symbol varchar(255),
        direction_type_list_id int4,
        display_id int4,
        text_format_id int4,
        dashed_formatting_id int4,
        metronome_mark_1_id int4,
        metronome_mark_2_id int4,
        primary key (id)
    );

    create table direction_type_list (
       id int4 not null,
        element_id varchar(255),
        direction_id int4,
        primary key (id)
    );

    create table directive (
       id int4 not null,
        lang varchar(255),
        value varchar(255),
        display_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table display (
       id int4 not null,
        color varchar(255),
        halign varchar(255),
        placement varchar(255),
        valign varchar(255),
        font_id int4,
        position_id int4,
        primary key (id)
    );

    create table distance (
       id int4 not null,
        type varchar(255),
        value numeric(12, 4),
        appearance_id int4 not null,
        primary key (id)
    );

    create table dot (
       id int4 not null,
        display_id int4,
        note_id int4 not null,
        primary key (id)
    );

    create table dynamics_marking (
       id int4 not null,
        dynamics_type varchar(255),
        smufl varchar(255),
        value varchar(255),
        dynamics_id int4 not null,
        primary key (id)
    );

    create table editorial (
       id int4 not null,
        footnote_id int4,
        level_id int4,
        primary key (id)
    );

    create table editorial_level (
       id int4 not null,
        reference char(1),
        value varchar(255),
        level_display_id int4,
        primary key (id)
    );

    create table element_position (
       id int4 not null,
        element varchar(255),
        position int4,
        primary key (id)
    );

    create table elision (
       id int4 not null,
        smufl varchar(255),
        value varchar(255),
        display_id int4,
        primary key (id)
    );

    create table encoding (
       encoding_type varchar(31) not null,
        id int4 not null,
        attribute varchar(255),
        element varchar(255),
        supports_type char(1),
        value varchar(255),
        software varchar(255),
        encoding_date timestamp,
        encoding_description varchar(255),
        encoder_id int4,
        identification_id int4 not null,
        primary key (id)
    );

    create table ending (
       id int4 not null,
        end_length numeric(12, 4),
        ending_number varchar(255),
        print_object char(1),
        text_x numeric(12, 4),
        text_y numeric(12, 4),
        ending_type varchar(255),
        value varchar(255),
        display_id int4,
        primary key (id)
    );

    create table except_voice (
       id int4 not null,
        value varchar(255),
        slash_group_id int4,
        primary key (id)
    );

    create table feature (
       id int4 not null,
        type varchar(255),
        value varchar(255),
        grouping_id int4 not null,
        primary key (id)
    );

    create table figure (
       id int4 not null,
        editorial_id int4,
        extend_id int4,
        figure_number_id int4,
        prefix_id int4,
        suffix_id int4,
        figured_bass_id int4 not null,
        primary key (id)
    );

    create table first_fret (
       id int4 not null,
        location varchar(255),
        text varchar(255),
        value int4,
        primary key (id)
    );

    create table font (
       id int4 not null,
        font_family varchar(255),
        font_size varchar(255),
        font_style varchar(255),
        font_weight varchar(255),
        primary key (id)
    );

    create table footnote (
       id int4 not null,
        display_id int4,
        text_format_id int4,
        primary key (id)
    );

    create table frame (
       id int4 not null,
        element_id varchar(255),
        frame_frets int4,
        frame_strings int4,
        height numeric(12, 4),
        unplayed varchar(255),
        width numeric(12, 4),
        display_id int4,
        first_fret_id int4,
        primary key (id)
    );

    create table frame_note (
       id int4 not null,
        barre_id int4,
        fingering_id int4,
        fret_id int4,
        string_id int4,
        frame_id int4 not null,
        primary key (id)
    );

    create table full_note (
       id int4 not null,
        chord char(1),
        full_note_type_id int4,
        primary key (id)
    );

    create table full_note_type (
       full_note_type_name varchar(31) not null,
        id int4 not null,
        pitch_alter numeric(12, 4),
        octave int4,
        step varchar(255),
        display_octave int4,
        display_step varchar(255),
        measure char(1),
        primary key (id)
    );

    create table glyph (
       id int4 not null,
        type varchar(255),
        value varchar(255),
        appearance_id int4 not null,
        primary key (id)
    );

    create table grace (
       id int4 not null,
        make_time numeric(12, 4),
        slash char(1),
        steal_time_following numeric(12, 4),
        steal_time_previous numeric(12, 4),
        primary key (id)
    );

    create table group_barline (
       id int4 not null,
        group_barline_type varchar(255),
        display_id int4,
        primary key (id)
    );

    create table group_name (
       id int4 not null,
        group_name varchar(255),
        justify varchar(255),
        display_id int4,
        primary key (id)
    );

    create table group_symbol (
       id int4 not null,
        group_symbol_type varchar(255),
        display_id int4,
        primary key (id)
    );

    create table harmon_closed (
       id int4 not null,
        location varchar(255),
        value varchar(255),
        primary key (id)
    );

    create table harmony_chord (
       harmony_chord_type varchar(31) not null,
        id int4 not null,
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
       id int4 not null,
        source varchar(255),
        miscellaneous_id int4,
        primary key (id)
    );

    create table instrument_type (
       instrument_type varchar(31) not null,
        id int4 not null,
        value int4,
        primary key (id)
    );

    create table interchangeable (
       id int4 not null,
        time_separator varchar(255),
        symbol varchar(255),
        time_relation varchar(255),
        time_signature_id int4,
        primary key (id)
    );

    create table inversion (
       id int4 not null,
        value int4,
        display_id int4,
        primary key (id)
    );

    create table key_octave (
       id int4 not null,
        cancel char(1),
        key_octave_number int4,
        octave int4,
        key_id int4 not null,
        primary key (id)
    );

    create table key_signature (
       key_type varchar(31) not null,
        id int4 not null,
        element_id varchar(255),
        key_number int4,
        print_object char(1),
        fifths int4,
        key_mode varchar(255),
        display_id int4,
        cancel_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table kind (
       id int4 not null,
        bracket_degrees char(1),
        kind_value varchar(255),
        parentheses_degrees char(1),
        stack_degrees char(1),
        text varchar(255),
        use_symbols char(1),
        display_id int4,
        primary key (id)
    );

    create table layout (
       id int4 not null,
        page_layout_id int4,
        systemm_layout_id int4,
        primary key (id)
    );

    create table level_display (
       id int4 not null,
        bracket char(1),
        parentheses char(1),
        symbol_size varchar(255),
        primary key (id)
    );

    create table line (
       id int4 not null,
        line_length varchar(255),
        line_shape varchar(255),
        line_type varchar(255),
        dashed_formatting_id int4,
        primary key (id)
    );

    create table line_width (
       id int4 not null,
        line_width_type varchar(255),
        value numeric(12, 4),
        appearance_id int4 not null,
        primary key (id)
    );

    create table link_attributes (
       id int4 not null,
        actuate varchar(255),
        href varchar(255),
        link_role varchar(255),
        link_show varchar(255),
        title varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table lyric (
       id int4 not null,
        element_id varchar(255),
        end_line char(1),
        end_paragraph char(1),
        justify varchar(255),
        name varchar(255),
        lyric_number varchar(255),
        print_object char(1),
        time_only varchar(255),
        display_id int4,
        editorial_id int4,
        lyric_item_id int4,
        note_id int4 not null,
        primary key (id)
    );

    create table lyric_font (
       id int4 not null,
        name varchar(255),
        lyric_font_number varchar(255),
        font_id int4,
        defaults_id int4 not null,
        primary key (id)
    );

    create table lyric_item (
       lyric_item_type varchar(31) not null,
        id int4 not null,
        type varchar(255),
        display_id int4,
        extend_id int4,
        primary key (id)
    );

    create table lyric_language (
       id int4 not null,
        lang varchar(255),
        name varchar(255),
        lyric_language_number varchar(255),
        defaults_id int4 not null,
        primary key (id)
    );

    create table lyric_syllable (
       id int4 not null,
        syllabic varchar(255),
        elision_id int4,
        text_data_id int4,
        lyric_text_id int4 not null,
        primary key (id)
    );

    create table lyric_text_data (
       id int4 not null,
        display_id int4,
        text_format_id int4,
        primary key (id)
    );

    create table margins (
       id int4 not null,
        bottom_margin numeric(12, 4),
        left_margin numeric(12, 4),
        right_margin numeric(12, 4),
        top_margin numeric(12, 4),
        primary key (id)
    );

    create table measure (
       id int4 not null,
        ordering int4,
        element_id varchar(255),
        implicit char(1),
        non_controlling char(1),
        measure_number varchar(255),
        text varchar(255),
        width numeric(12, 4),
        part_id int4 not null,
        primary key (id)
    );

    create table measure_layout (
       id int4 not null,
        measure_distance numeric(12, 4),
        primary key (id)
    );

    create table measure_style (
       measure_style_type varchar(31) not null,
        id int4 not null,
        element_id varchar(255),
        measure_style_number int4,
        use_symbols char(1),
        value int4,
        type varchar(255),
        use_dots char(1),
        use_stems char(1),
        slashes int4,
        display_id int4,
        slash_group_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table metronome_beam (
       id int4 not null,
        beam_type varchar(255),
        metronome_beam_number int4,
        metronome_note_id int4 not null,
        primary key (id)
    );

    create table metronome_mark (
       metronome_mark_type varchar(31) not null,
        id int4 not null,
        per_minute varchar(255),
        beat_unit varchar(255),
        beat_unit_dots int4,
        display_id int4,
        primary key (id)
    );

    create table metronome_note (
       id int4 not null,
        metronome_dots int4,
        metronome_tied varchar(255),
        metronome_type varchar(255),
        metronome_tuplet_id int4,
        note_metronome_2_id int4 not null,
        note_metronome_1_id int4 not null,
        primary key (id)
    );

    create table metronome_tuplet (
       id int4 not null,
        bracket char(1),
        show_number varchar(255),
        metronome_tuplet_type varchar(255),
        time_modification_id int4,
        primary key (id)
    );

    create table midi_device (
       id int4 not null,
        midi_device_id varchar(255),
        port int4,
        value varchar(255),
        primary key (id)
    );

    create table midi_instrument (
       id int4 not null,
        elevation numeric(12, 4),
        midi_bank int4,
        midi_channel int4,
        midi_instrument_id varchar(255),
        midi_name varchar(255),
        midi_program int4,
        midi_unpitched int4,
        pan numeric(12, 4),
        volume numeric(12, 4),
        primary key (id)
    );

    create table miscellaneous (
       id int4 not null,
        primary key (id)
    );

    create table miscellaneous_field (
       id int4 not null,
        name varchar(255),
        value varchar(255),
        miscellaneous_id int4 not null,
        primary key (id)
    );

    create table music_data (
       music_data_type varchar(31) not null,
        id int4 not null,
        ordering int4,
        duration numeric(12, 4),
        element_id varchar(255),
        coda varchar(255),
        dacapo char(1),
        dalsegno varchar(255),
        damper_pedal varchar(255),
        divisions numeric(12, 4),
        dynamics numeric(12, 4),
        elevation numeric(12, 4),
        fine varchar(255),
        forward_repeat char(1),
        pan numeric(12, 4),
        pizzicato char(1),
        segno varchar(255),
        soft_pedal varchar(255),
        sostenuto_pedal varchar(255),
        tempo numeric(12, 4),
        time_only varchar(255),
        tocoda varchar(255),
        parentheses char(1),
        instruments int4,
        staves int4,
        print_frame char(1),
        print_object char(1),
        staff int4,
        type varchar(255),
        bar_style varchar(255),
        location varchar(255),
        blank_page int4,
        measure_numbering_value varchar(255),
        new_page char(1),
        new_system char(1),
        page_number varchar(255),
        staff_spacing numeric(12, 4),
        grouping_number varchar(255),
        number_of varchar(255),
        bookmark_id varchar(255),
        name varchar(255),
        voice varchar(255),
        attack_length numeric(12, 4),
        cue char(1),
        end_dynamics numeric(12, 4),
        instrument varchar(255),
        printLeger char(1),
        release_length numeric(12, 4),
        directive char(1),
        measure_id int4,
        editorial_id int4,
        offset_id int4,
        display_id int4,
        printout_id int4,
        part_symbol_id int4,
        frame_id int4,
        coda_id int4,
        ending_id int4,
        repeat_id int4,
        segno_id int4,
        wavy_line_id int4,
        layout_id int4,
        measure_layout_id int4,
        part_abbreviation_display_id int4,
        part_name_display_id int4,
        credit_display_id int4,
        element_position_id int4,
        link_attributes_id int4,
        accidental_id int4,
        full_note_id int4,
        grace_id int4,
        notehead_id int4,
        notehead_text_id int4,
        play_id int4,
        stem_id int4,
        time_modification_id int4,
        type_id int4,
        sound_id int4,
        primary key (id)
    );

    create table name_display (
       id int4 not null,
        print_object char(1),
        primary key (id)
    );

    create table non_traditional_key_type (
       id int4 not null,
        key_accidental varchar(255),
        key_accidental_smufl varchar(255),
        key_alter numeric(12, 4),
        key_step varchar(255),
        non_traditional_key_id int4 not null,
        primary key (id)
    );

    create table notation (
       notation_type varchar(31) not null,
        id int4 not null,
        element_id varchar(255),
        notation_number int4,
        type_value varchar(255),
        print_object char(1),
        smufl varchar(255),
        value varchar(255),
        line_type varchar(255),
        connection_type varchar(255),
        orientation varchar(255),
        fermata_shape varchar(255),
        direction varchar(255),
        bracket char(1),
        line_shape varchar(255),
        show_number varchar(255),
        show_type varchar(255),
        tied_type varchar(255),
        accidental_type varchar(255),
        display_id int4,
        notations_id int4,
        dashed_formatting_id int4,
        bezier_id int4,
        bend_sound_id int4,
        barline_id int4,
        tuplet_actual_id int4,
        tuplet_normal_id int4,
        dynamics_id int4,
        level_display_id int4,
        primary key (id)
    );

    create table notations (
       id int4 not null,
        element_id varchar(255),
        print_object char(1),
        editorial_id int4,
        note_id int4 not null,
        primary key (id)
    );

    create table note_size (
       id int4 not null,
        type varchar(255),
        value numeric(12, 4),
        appearance_id int4 not null,
        primary key (id)
    );

    create table note_type (
       id int4 not null,
        symbol_size varchar(255),
        note_type_size varchar(255),
        primary key (id)
    );

    create table notehead (
       id int4 not null,
        filled char(1),
        parentheses char(1),
        smufl varchar(255),
        type varchar(255),
        display_id int4,
        primary key (id)
    );

    create table notehead_text (
       id int4 not null,
        primary key (id)
    );

    create table ornament (
       ornament_type varchar(31) not null,
        id int4 not null,
        smufl varchar(255),
        value varchar(255),
        slash char(1),
        wavy_line_number int4,
        connection_type varchar(255),
        tremolo_marks int4,
        tremolo_type varchar(255),
        approach varchar(255),
        departure varchar(255),
        long_mordent char(1),
        display_id int4,
        ornaments_id int4,
        placement_text_id int4,
        trill_sound_id int4,
        primary key (id)
    );

    create table ornament_accidental (
       id int4 not null,
        accidental_mark_id int4,
        ornaments_id int4 not null,
        primary key (id)
    );

    create table other_appearance (
       id int4 not null,
        type varchar(255),
        value varchar(255),
        appearance_id int4 not null,
        primary key (id)
    );

    create table page_layout (
       id int4 not null,
        page_height numeric(12, 4),
        page_width numeric(12, 4),
        primary key (id)
    );

    create table page_margins (
       id int4 not null,
        margin_type_key varchar(255),
        type varchar(255),
        margins_id int4,
        page_layout_id int4 not null,
        primary key (id)
    );

    create table part (
       id int4 not null,
        ordering int4,
        element_id varchar(255),
        part_id varchar(255),
        score_id int4 not null,
        primary key (id)
    );

    create table part_item (
       part_item_type varchar(31) not null,
        id int4 not null,
        ordering int4,
        element_id varchar(255),
        group_time char(1),
        part_group_number varchar(255),
        part_group_type varchar(255),
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
       id int4 not null,
        primary key (id)
    );

    create table part_name (
       id int4 not null,
        justify varchar(255),
        part_name varchar(255),
        print_object char(1),
        display_id int4,
        primary key (id)
    );

    create table part_symbol (
       id int4 not null,
        bottom_staff int4,
        group_symbol_type varchar(255),
        top_staff int4,
        display_id int4,
        primary key (id)
    );

    create table pedal_tuning (
       id int4 not null,
        pedal_alter numeric(12, 4),
        pedal_step varchar(255),
        harp_pedals_id int4 not null,
        primary key (id)
    );

    create table placement_text (
       id int4 not null,
        value varchar(255),
        display_id int4,
        primary key (id)
    );

    create table play (
       id int4 not null,
        play_id varchar(255),
        primary key (id)
    );

    create table play_type (
       play_type_type varchar(31) not null,
        id int4 not null,
        value varchar(255),
        type varchar(255),
        play_id int4,
        primary key (id)
    );

    create table position (
       id int4 not null,
        default_x numeric(12, 4),
        default_y numeric(12, 4),
        relative_x numeric(12, 4),
        relative_y numeric(12, 4),
        primary key (id)
    );

    create table printout (
       id int4 not null,
        print_dot char(1),
        print_lyric char(1),
        print_object char(1),
        print_spacing char(1),
        primary key (id)
    );

    create table root_alter (
       id int4 not null,
        location varchar(255),
        print_object char(1),
        semitones numeric(12, 4),
        display_id int4,
        primary key (id)
    );

    create table root_step (
       id int4 not null,
        step varchar(255),
        text varchar(255),
        display_id int4,
        primary key (id)
    );

    create table scaling (
       id int4 not null,
        millimeters numeric(12, 4),
        tenths numeric(12, 4),
        primary key (id)
    );

    create table score (
       id int4 not null,
        score_name varchar(255),
        version varchar(255),
        score_header_id int4,
        primary key (id)
    );

    create table score_header (
       id int4 not null,
        movement_number varchar(255),
        movement_title varchar(255),
        defaults_id int4,
        identification_id int4,
        part_list_id int4,
        work_id int4,
        primary key (id)
    );

    create table score_instrument (
       id int4 not null,
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
       id int4 not null,
        midi_device_id int4,
        midi_instrument_id int4,
        score_part_id int4 not null,
        primary key (id)
    );

    create table score_part_group (
       id int4 not null,
        group_name varchar(255),
        score_part_id int4 not null,
        primary key (id)
    );

    create table slash_group (
       id int4 not null,
        slash_dots int4,
        slash_type varchar(255),
        primary key (id)
    );

    create table sound_midi (
       id int4 not null,
        midi_device_id int4,
        midi_instrument_id int4,
        play_id int4,
        sound_id int4 not null,
        primary key (id)
    );

    create table staff_details (
       id int4 not null,
        capo int4,
        staff_details_number int4,
        print_object char(1),
        print_spacing char(1),
        show_frets varchar(255),
        staff_lines int4,
        staff_size numeric(12, 4),
        staff_type varchar(255),
        attributes_id int4 not null,
        primary key (id)
    );

    create table staff_layout (
       id int4 not null,
        staff_layout_number int4,
        staff_distance numeric(12, 4),
        layout_id int4 not null,
        primary key (id)
    );

    create table staff_tuning (
       id int4 not null,
        line int4,
        tuning_id int4,
        staff_details_id int4 not null,
        primary key (id)
    );

    create table stem (
       id int4 not null,
        type varchar(255),
        display_id int4,
        primary key (id)
    );

    create table style_text (
       id int4 not null,
        value varchar(255),
        display_id int4,
        primary key (id)
    );

    create table system_divider (
       id int4 not null,
        print_object char(1),
        display_id int4,
        primary key (id)
    );

    create table system_dividers (
       id int4 not null,
        left_divider_id int4,
        right_divider_id int4,
        primary key (id)
    );

    create table system_layout (
       id int4 not null,
        left_margin numeric(12, 4),
        right_margin numeric(12, 4),
        system_distance numeric(12, 4),
        top_system_distance numeric(12, 4),
        system_dividers_id int4,
        primary key (id)
    );

    create table technical (
       technical_type varchar(31) not null,
        id int4 not null,
        string_number int4,
        alternate char(1),
        substitution char(1),
        value varchar(255),
        notation_number int4,
        notation_type varchar(255),
        smufl varchar(255),
        arrow_direction varchar(255),
        arrow_style varchar(255),
        arrowhead char(1),
        circular_arrow varchar(255),
        hole_closed_location varchar(255),
        hole_close_type varchar(255),
        hole_shape varchar(255),
        hole_type varchar(255),
        tap_hand varchar(255),
        bend_alter numeric(12, 4),
        bend_type varchar(255),
        handbell_type varchar(255),
        harmonic_pitch varchar(255),
        harmonic_type varchar(255),
        print_object char(1),
        display_id int4,
        technicals_id int4,
        harmon_closed_id int4,
        bend_sound_id int4,
        with_bar_id int4,
        primary key (id)
    );

    create table text_display (
       text_type varchar(31) not null,
        id int4 not null,
        accidental_type varchar(255),
        smufl varchar(255),
        display_id int4,
        text_format_id int4,
        notehead_text_id int4,
        name_display_id int4,
        primary key (id)
    );

    create table text_format (
       id int4 not null,
        enclosure varchar(255),
        justify varchar(255),
        lang varchar(255),
        letter_spacing varchar(255),
        line_height varchar(255),
        line_through int4,
        overline int4,
        space varchar(255),
        text_direction varchar(255),
        text_rotation numeric(12, 4),
        underline int4,
        value varchar(255),
        primary key (id)
    );

    create table tie (
       id int4 not null,
        time_only varchar(255),
        type varchar(255),
        note_id int4 not null,
        primary key (id)
    );

    create table time (
       time_type varchar(31) not null,
        id int4 not null,
        element_id varchar(255),
        time_number int4,
        print_object char(1),
        time_separator varchar(255),
        symbol varchar(255),
        value varchar(255),
        display_id int4,
        interchangeable_id int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table time_modification (
       id int4 not null,
        actual_notes int4,
        normal_dots int4,
        normal_notes int4,
        normal_type varchar(255),
        primary key (id)
    );

    create table time_signature_type (
       id int4 not null,
        beat_type varchar(255),
        beats varchar(255),
        time_signature_id int4 not null,
        primary key (id)
    );

    create table transpose (
       id int4 not null,
        element_id varchar(255),
        chromatic numeric(12, 4),
        diatonic int4,
        doubled char(1),
        transpose_number int4,
        octave_change int4,
        attributes_id int4 not null,
        primary key (id)
    );

    create table trill_sound (
       id int4 not null,
        accelerate char(1),
        beats numeric(12, 4),
        last_beat numeric(12, 4),
        second_beat numeric(12, 4),
        start_note varchar(255),
        trill_step varchar(255),
        two_note_turn varchar(255),
        primary key (id)
    );

    create table tuning (
       id int4 not null,
        tuning_alter numeric(12, 4),
        tuning_octave int4,
        tuning_step varchar(255),
        primary key (id)
    );

    create table tuplet_dot (
       id int4 not null,
        display_id int4,
        tuplet_portion_id int4 not null,
        primary key (id)
    );

    create table tuplet_number (
       id int4 not null,
        value int4,
        display_id int4,
        primary key (id)
    );

    create table tuplet_portion (
       id int4 not null,
        tuplet_number_id int4,
        tuplet_type_id int4,
        primary key (id)
    );

    create table tuplet_type (
       id int4 not null,
        note_type_value varchar(255),
        display_id int4,
        primary key (id)
    );

    create table typed_text (
       id int4 not null,
        type varchar(255),
        value varchar(255),
        creator_id int4,
        relation_id int4,
        rights_id int4,
        primary key (id)
    );

    create table virtual_instrument (
       id int4 not null,
        virtual_library varchar(255),
        virtual_name varchar(255),
        primary key (id)
    );

    create table work (
       id int4 not null,
        work_number varchar(255),
        work_title varchar(255),
        opus_id int4,
        primary key (id)
    );

    create table xml_comment (
       id int4 not null,
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
       add constraint FKog7p17vaochq32sjs8bjxv1v4 
       foreign key (display_id) 
       references display;

    alter table accidental 
       add constraint FKf1brlbu5kohjtiexhuaj1uv1j 
       foreign key (level_display_id) 
       references level_display;

    alter table accord 
       add constraint FKmallx8sq3vhlhproso60l144t 
       foreign key (tuning_id) 
       references tuning;

    alter table accord 
       add constraint FKtr8qmjwj8ddfyk2gi4wmhlm5a 
       foreign key (scordatura_id) 
       references direction_type;

    alter table articulation 
       add constraint FKnn2i78j4t94y2sch4dxc9bj4x 
       foreign key (display_id) 
       references display;

    alter table articulation 
       add constraint FK30m8vy3kinx56q1w2xbmp3bty 
       foreign key (line_id) 
       references line;

    alter table articulation 
       add constraint FK1emxxnei3prqc823q15gt5ccp 
       foreign key (articulations_id) 
       references notation;

    alter table barre 
       add constraint FKfympa43emgp0kyy1qqbrtqnow 
       foreign key (display_id) 
       references display;

    alter table bass 
       add constraint FK89b8ei38psnad41dlrveugi8i 
       foreign key (bass_alter_id) 
       references bass_alter;

    alter table bass 
       add constraint FKatrignlphdp7f28hwjriexyo0 
       foreign key (bass_step_id) 
       references bass_step;

    alter table bass_alter 
       add constraint FK2ty4w07l9qp4tse2qqdf2u68s 
       foreign key (display_id) 
       references display;

    alter table bass_step 
       add constraint FKptfr45ox5vrmlw4pa2vbf2apu 
       foreign key (display_id) 
       references display;

    alter table beam 
       add constraint FK3r7dqpis0rakfenlmnbtdvs4 
       foreign key (display_id) 
       references display;

    alter table beam 
       add constraint FKbirfijk9ipkvby7y04lbehl9i 
       foreign key (note_id) 
       references music_data;

    alter table beat_unit_tied 
       add constraint FKbjm8lcptks4j06iky0ur8u15e 
       foreign key (beat_unit_tied_parent_id) 
       references metronome_mark;

    alter table beat_unit_tied 
       add constraint FK5pl5jidyehnc0ca9999sky4hk 
       foreign key (beat_unit_parent_id) 
       references metronome_mark;

    alter table clef 
       add constraint FKa378tnca4yd6yylyai6yl86uf 
       foreign key (display_id) 
       references display;

    alter table clef 
       add constraint FKi3ndwd0j6jwnc9b4bs9qrhra8 
       foreign key (attributes_id) 
       references music_data;

    alter table credit 
       add constraint FKqt3t8jsqw9f8epk2mg3cbvogs 
       foreign key (score_header_id) 
       references score_header;

    alter table credit_display 
       add constraint FKlyds4j445i0ws1sv85ocihbmp 
       foreign key (display_id) 
       references display;

    alter table credit_display 
       add constraint FKlc9mqx1m5cix2cr4vt6p1at09 
       foreign key (text_format_id) 
       references text_format;

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
       add constraint FKln9u0gr6hmcmb9tp1e401v7gq 
       foreign key (display_id) 
       references display;

    alter table degree_type 
       add constraint FKt02dg5fdo8fu2fcmy42216mnb 
       foreign key (display_id) 
       references display;

    alter table degree_value 
       add constraint FKhddv8q1fky1rcpq7b78200yd2 
       foreign key (display_id) 
       references display;

    alter table direction_type 
       add constraint FKg4bem6dw87egwlbf56tdtr3ae 
       foreign key (direction_type_list_id) 
       references direction_type_list;

    alter table direction_type 
       add constraint FK77ibcdkbyqisesln0qu89w1k9 
       foreign key (display_id) 
       references display;

    alter table direction_type 
       add constraint FK8aw4acynt9n77ipwwgwppdp9s 
       foreign key (text_format_id) 
       references text_format;

    alter table direction_type 
       add constraint FKgxses1obcssr9l23473nifsgy 
       foreign key (dashed_formatting_id) 
       references dashed_formatting;

    alter table direction_type 
       add constraint FKsxbv146gynnqnw6nab75n1ba9 
       foreign key (metronome_mark_1_id) 
       references metronome_mark;

    alter table direction_type 
       add constraint FKesly754x1a2cyws3u2mrwmrym 
       foreign key (metronome_mark_2_id) 
       references metronome_mark;

    alter table direction_type_list 
       add constraint FKblbd4ond4mhyu4o5cd0blqrsm 
       foreign key (direction_id) 
       references music_data;

    alter table directive 
       add constraint FKolp4d7yqveg72d8nhbmp3kx8r 
       foreign key (display_id) 
       references display;

    alter table directive 
       add constraint FK7ksvq9gvmn531kkcf9yodu3be 
       foreign key (attributes_id) 
       references music_data;

    alter table display 
       add constraint FKojhuhxyhklq2hjisna3o24n1v 
       foreign key (font_id) 
       references font;

    alter table display 
       add constraint FK2107b5khj7dpia54m5mlscu2h 
       foreign key (position_id) 
       references position;

    alter table distance 
       add constraint FKh7upf2u0uver0s5p0wbtug9y 
       foreign key (appearance_id) 
       references appearance;

    alter table dot 
       add constraint FKj1btpaoyc9nvstpp5k8f06lj8 
       foreign key (display_id) 
       references display;

    alter table dot 
       add constraint FKinehtcky1m5rv5wexupjmixs 
       foreign key (note_id) 
       references music_data;

    alter table dynamics_marking 
       add constraint FKpg0vojk8lfwqhy6wpuo8xy36a 
       foreign key (dynamics_id) 
       references direction_type;

    alter table editorial 
       add constraint FKgd6ji3ihwlf2njs9w5gl6b550 
       foreign key (footnote_id) 
       references footnote;

    alter table editorial 
       add constraint FK1l4jxj9yu15r99y7n5tmpe483 
       foreign key (level_id) 
       references editorial_level;

    alter table editorial_level 
       add constraint FKjrho9g6v6tm3i5ervhove93oe 
       foreign key (level_display_id) 
       references level_display;

    alter table elision 
       add constraint FKm7ixjbc10372o8tbtxfyp7dec 
       foreign key (display_id) 
       references display;

    alter table encoding 
       add constraint FK8l5bbf29jxhcjw32xffoh6lqe 
       foreign key (encoder_id) 
       references typed_text;

    alter table encoding 
       add constraint FKj8g0yqb3iyn82s4pfgrlolqvl 
       foreign key (identification_id) 
       references identification;

    alter table ending 
       add constraint FK9my78uy851yr2gddl0ihwrj21 
       foreign key (display_id) 
       references display;

    alter table except_voice 
       add constraint FKnm4i9hxd6b7xb19cqiywqya2e 
       foreign key (slash_group_id) 
       references slash_group;

    alter table feature 
       add constraint FKmul6tobnvi4eghf76th7h3mdq 
       foreign key (grouping_id) 
       references music_data;

    alter table figure 
       add constraint FK3t1vsii1hkcl4ool8vfnkwtug 
       foreign key (editorial_id) 
       references editorial;

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

    alter table footnote 
       add constraint FKojw33aa3lklit05wgqekf3yxw 
       foreign key (display_id) 
       references display;

    alter table footnote 
       add constraint FKo822s5f1w4n3alf5oe205qbnj 
       foreign key (text_format_id) 
       references text_format;

    alter table frame 
       add constraint FKg86hx52tr4n9nbl5bxjnqyw00 
       foreign key (display_id) 
       references display;

    alter table frame 
       add constraint FKbu912vqnib5jvntkcd275bsb7 
       foreign key (first_fret_id) 
       references first_fret;

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

    alter table glyph 
       add constraint FKo1nificvy6udwyn1oaws82a55 
       foreign key (appearance_id) 
       references appearance;

    alter table group_barline 
       add constraint FKfig7m4m884wx19gesoojlgtg3 
       foreign key (display_id) 
       references display;

    alter table group_name 
       add constraint FKdpc0x3kr0ntfsiqe03eiia154 
       foreign key (display_id) 
       references display;

    alter table group_symbol 
       add constraint FK3vsrxsapgrc630v5w13nrv739 
       foreign key (display_id) 
       references display;

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
       add constraint FK8wmswbqbiahcr1tbssadhs3d6 
       foreign key (display_id) 
       references display;

    alter table key_octave 
       add constraint FKkt0hsurbjdyb1x6jjdfj3j9h6 
       foreign key (key_id) 
       references key_signature;

    alter table key_signature 
       add constraint FKcbiju7t2tfmedojiu5hgnoup4 
       foreign key (display_id) 
       references display;

    alter table key_signature 
       add constraint FK82qm60fbbgff6m3dn8txr0dko 
       foreign key (cancel_id) 
       references cancel;

    alter table key_signature 
       add constraint FKddfd76kt7qik3tiygvjx249wx 
       foreign key (attributes_id) 
       references music_data;

    alter table kind 
       add constraint FKbqjf1wkdh2ki6thj3fvqivpv 
       foreign key (display_id) 
       references display;

    alter table layout 
       add constraint FK552vkgbeaq1y95tslmngsb7yi 
       foreign key (page_layout_id) 
       references page_layout;

    alter table layout 
       add constraint FK7smcened627hb3fwcsewg4gyv 
       foreign key (systemm_layout_id) 
       references system_layout;

    alter table line 
       add constraint FKom9rcpsr4cn14tt4k2jarkhg9 
       foreign key (dashed_formatting_id) 
       references dashed_formatting;

    alter table line_width 
       add constraint FKolk6o737efsh34fe1lbl3646t 
       foreign key (appearance_id) 
       references appearance;

    alter table lyric 
       add constraint FK3wlvu2d93tldinxvxu9omcly2 
       foreign key (display_id) 
       references display;

    alter table lyric 
       add constraint FKjyeuo5oulnpb4hiid8deb75ot 
       foreign key (editorial_id) 
       references editorial;

    alter table lyric 
       add constraint FKog91nc853a53gcxgbvoqaji70 
       foreign key (lyric_item_id) 
       references lyric_item;

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
       add constraint FK6gdrh9hd83f38euwdfx1nggul 
       foreign key (display_id) 
       references display;

    alter table lyric_item 
       add constraint FKbjmewleq4j31k1dtppbtcp4dh 
       foreign key (extend_id) 
       references lyric_item;

    alter table lyric_language 
       add constraint FKn8jv5f6ji369lamut0desxu0k 
       foreign key (defaults_id) 
       references defaults;

    alter table lyric_syllable 
       add constraint FKlmk9aphu2dxt9l8h0c4wkgrso 
       foreign key (elision_id) 
       references elision;

    alter table lyric_syllable 
       add constraint FKc431ui8rpt5kwsckw8p0y80yc 
       foreign key (text_data_id) 
       references lyric_text_data;

    alter table lyric_syllable 
       add constraint FK25xj1xbkynu8c87129s2qodt7 
       foreign key (lyric_text_id) 
       references lyric_item;

    alter table lyric_text_data 
       add constraint FKmc2stvngctieoh7j6k407j70v 
       foreign key (display_id) 
       references display;

    alter table lyric_text_data 
       add constraint FKfw1oy1qgsq3mk72oitwh18r4v 
       foreign key (text_format_id) 
       references text_format;

    alter table measure 
       add constraint FK7okawlbg5nyqon30ll7uavx28 
       foreign key (part_id) 
       references part;

    alter table measure_style 
       add constraint FKj3lm680yi0h9ikyq1w4uljq1v 
       foreign key (display_id) 
       references display;

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

    alter table metronome_mark 
       add constraint FKqfv6oplc70lkwvhimrio2f5vi 
       foreign key (display_id) 
       references display;

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
       add constraint FKk94ghor1mdy12w2m5eis6g1vj 
       foreign key (display_id) 
       references display;

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
       add constraint FK74wouy3pwlsp1e3l1rpjs4wer 
       foreign key (coda_id) 
       references direction_type;

    alter table music_data 
       add constraint FK6nxn99o8j8pik2kry92d4xs60 
       foreign key (ending_id) 
       references ending;

    alter table music_data 
       add constraint FKn81inqqf1uecp9lrqpoece8pb 
       foreign key (repeat_id) 
       references barline_repeat;

    alter table music_data 
       add constraint FKsdkthf201p0rbpcgkt00k0efe 
       foreign key (segno_id) 
       references direction_type;

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
       add constraint FKowu22dl3y19shmrdmjxok999l 
       foreign key (credit_display_id) 
       references credit_display;

    alter table music_data 
       add constraint FKs9hdr0f6wuyl1vc4o87odv54w 
       foreign key (element_position_id) 
       references element_position;

    alter table music_data 
       add constraint FK8l5ctbd1hibellcrcge7oob41 
       foreign key (link_attributes_id) 
       references link_attributes;

    alter table music_data 
       add constraint FKrbocaap3axrypmkiowm32k1pu 
       foreign key (accidental_id) 
       references accidental;

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
       add constraint FKex8und0d9f4odkobxomoq7ia4 
       foreign key (sound_id) 
       references music_data;

    alter table non_traditional_key_type 
       add constraint FKq72vcji3in5uw4f22rylm92y 
       foreign key (non_traditional_key_id) 
       references key_signature;

    alter table notation 
       add constraint FK5j2otsml2aexns8nkdp708hys 
       foreign key (display_id) 
       references display;

    alter table notation 
       add constraint FK5r5gx0kjtp620dora5pb9b4bf 
       foreign key (notations_id) 
       references notations;

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

    alter table notation 
       add constraint FKtna2ume78xg3ua7mnvb4r2my0 
       foreign key (level_display_id) 
       references level_display;

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
       add constraint FKr01qgxcl50q2q6jlxpr70msij 
       foreign key (display_id) 
       references display;

    alter table ornament 
       add constraint FK2yyq8ugyye0en8tmbswevkylp 
       foreign key (display_id) 
       references display;

    alter table ornament 
       add constraint FKnmqa6fuo6igfi4jv79gh6dwxs 
       foreign key (ornaments_id) 
       references notation;

    alter table ornament 
       add constraint FKoy2jywq7553xrg55v0fybhnsr 
       foreign key (placement_text_id) 
       references placement_text;

    alter table ornament 
       add constraint FKicpik38a8cvj0yg8ke41uth0n 
       foreign key (trill_sound_id) 
       references trill_sound;

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
       add constraint FK8w6uv4o5fbjq94cjtmr2r3ey6 
       foreign key (display_id) 
       references display;

    alter table part_symbol 
       add constraint FKlbmkyd7lwrme1ij4f1oa7cm6v 
       foreign key (display_id) 
       references display;

    alter table pedal_tuning 
       add constraint FKkvm1vni5oi78rbwmadfpcymb8 
       foreign key (harp_pedals_id) 
       references direction_type;

    alter table placement_text 
       add constraint FK9rjj7nwpivoe3h1dy647ekgiw 
       foreign key (display_id) 
       references display;

    alter table play_type 
       add constraint FKlqyvck4o1sx2npdvpvi2cr0iq 
       foreign key (play_id) 
       references play;

    alter table root_alter 
       add constraint FKpe18eow0hfasmtm48jnbb71dm 
       foreign key (display_id) 
       references display;

    alter table root_step 
       add constraint FKkpn77a163f8k4cg1ckvfmxdnq 
       foreign key (display_id) 
       references display;

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
       add constraint FKo8qmj3omyxak4f1olkmp2ifba 
       foreign key (display_id) 
       references display;

    alter table style_text 
       add constraint FKck74ubo69ygoe7d4milqjlbpj 
       foreign key (display_id) 
       references display;

    alter table system_divider 
       add constraint FK6dqjq9npjicmhrnrs1aqyb8eu 
       foreign key (display_id) 
       references display;

    alter table system_dividers 
       add constraint FKr3lmv8t3a4lohruqmpx2uohps 
       foreign key (left_divider_id) 
       references system_divider;

    alter table system_dividers 
       add constraint FKllkl0vvbn97n4p7ud0blfi17i 
       foreign key (right_divider_id) 
       references system_divider;

    alter table system_layout 
       add constraint FKoum2iw0o827holjk4avmetxpt 
       foreign key (system_dividers_id) 
       references system_dividers;

    alter table technical 
       add constraint FK47clxoexh6shyk117acm8bnan 
       foreign key (display_id) 
       references display;

    alter table technical 
       add constraint FKk1p1j3espd2ej3cfk81elx7qd 
       foreign key (technicals_id) 
       references notation;

    alter table technical 
       add constraint FKba4ghcyirr73ueblh3hmenkk9 
       foreign key (harmon_closed_id) 
       references harmon_closed;

    alter table technical 
       add constraint FKk2qoca6rp76q8da4xyyk8mpug 
       foreign key (bend_sound_id) 
       references bend_sound;

    alter table technical 
       add constraint FKm76u1honwnfwuj1u3tx82fooj 
       foreign key (with_bar_id) 
       references placement_text;

    alter table text_display 
       add constraint FK35uy6rwu1j5poqmgoo0ft68vl 
       foreign key (display_id) 
       references display;

    alter table text_display 
       add constraint FKiv1yjuhytayvijhq3px65i9e6 
       foreign key (text_format_id) 
       references text_format;

    alter table text_display 
       add constraint FKgcm9yg2bcd4ubd3a5dyxef50v 
       foreign key (notehead_text_id) 
       references notehead_text;

    alter table text_display 
       add constraint FKewbqxuv4t3j4je18bc13ni6je 
       foreign key (name_display_id) 
       references name_display;

    alter table tie 
       add constraint FKj5k6akoif382o12wegpt952g9 
       foreign key (note_id) 
       references music_data;

    alter table time 
       add constraint FK41fs0r29g8fwdacunpung5jsg 
       foreign key (display_id) 
       references display;

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
       add constraint FKhfuiuu221ljpaisx7x9tmi33 
       foreign key (display_id) 
       references display;

    alter table tuplet_dot 
       add constraint FK17shk9waf0d00k9f69xprvof8 
       foreign key (tuplet_portion_id) 
       references tuplet_portion;

    alter table tuplet_number 
       add constraint FKh2p1yjera7mawbronu39jqjop 
       foreign key (display_id) 
       references display;

    alter table tuplet_portion 
       add constraint FK7vx5g52oawiuajlnkhv6atbyc 
       foreign key (tuplet_number_id) 
       references tuplet_number;

    alter table tuplet_portion 
       add constraint FKtwl390gk0ghoye5fu7tnmyef 
       foreign key (tuplet_type_id) 
       references tuplet_type;

    alter table tuplet_type 
       add constraint FK9rxhpn2trkuhy5i5s2yp9ogdy 
       foreign key (display_id) 
       references display;

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
