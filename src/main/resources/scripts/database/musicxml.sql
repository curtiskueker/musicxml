
    create table accidental (
       id integer not null auto_increment,
        accidental_type varchar(255),
        cautionary bit,
        editorial bit,
        level_display_id integer,
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table accord (
       id integer not null auto_increment,
        string integer,
        tuning_id integer,
        scordatura_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table appearance (
       id integer not null auto_increment,
        primary key (id)
    ) engine=MyISAM;

    create table articulation (
       articulation_type varchar(31) not null,
        id integer not null auto_increment,
        breath_mark_value varchar(255),
        placement varchar(255),
        type varchar(255),
        print_style_id integer,
        placement_id integer,
        line_id integer,
        placement_text_id integer,
        articulations_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table bar_style_color (
       id integer not null auto_increment,
        bar_style varchar(255),
        color varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table barline_repeat (
       id integer not null auto_increment,
        direction varchar(255),
        times integer,
        winged varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table barre (
       id integer not null auto_increment,
        color varchar(255),
        type varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table bass (
       id integer not null auto_increment,
        bass_alter_id integer,
        bass_step_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table bass_alter (
       id integer not null auto_increment,
        location varchar(255),
        print_object bit,
        semitones decimal(12,4),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table bass_step (
       id integer not null auto_increment,
        step varchar(255),
        text varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table beam (
       id integer not null auto_increment,
        color varchar(255),
        fan varchar(255),
        number integer,
        repeater bit,
        type varchar(255),
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table beat_unit (
       id integer not null auto_increment,
        beat_unit varchar(255),
        beat_unit_dots integer,
        primary key (id)
    ) engine=MyISAM;

    create table bend_sound (
       id integer not null auto_increment,
        accelerate bit,
        beats decimal(12,4),
        first_beat decimal(12,4),
        last_beat decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table bezier (
       id integer not null auto_increment,
        bezier_offset decimal(12,4),
        bezier_offset2 decimal(12,4),
        bezier_x decimal(12,4),
        bezier_x2 decimal(12,4),
        bezier_y decimal(12,4),
        bezier_y2 decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table cancel (
       id integer not null auto_increment,
        fifths integer,
        location varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table clef (
       id integer not null auto_increment,
        additional bit,
        after_barline bit,
        clef_octave_change integer,
        line integer,
        number integer,
        print_object bit,
        sign varchar(255),
        size varchar(255),
        print_style_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table credit (
       id integer not null auto_increment,
        page integer,
        score_header_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table credit_display (
       credit_display_type varchar(31) not null,
        id integer not null auto_increment,
        formatted_text_id integer,
        image_id integer,
        credit_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table credit_type (
       id integer not null auto_increment,
        type varchar(255),
        credit_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table dashed_formatting (
       id integer not null auto_increment,
        dash_length decimal(12,4),
        space_length decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table defaults (
       id integer not null auto_increment,
        appearance_id integer,
        layout_id integer,
        music_font_id integer,
        defaults_id integer,
        word_font_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table degree (
       id integer not null auto_increment,
        print_object bit,
        degree_alter_id integer,
        degree_type_id integer,
        degree_value_id integer,
        harmony_chord_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table degree_alter (
       id integer not null auto_increment,
        plus_minus bit,
        semitones decimal(12,4),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table degree_type (
       id integer not null auto_increment,
        text varchar(255),
        value varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table degree_value (
       id integer not null auto_increment,
        symbol varchar(255),
        text varchar(255),
        value integer,
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table direction_type (
       direction_type varchar(31) not null,
        id integer not null auto_increment,
        enclosure varchar(255),
        type varchar(255),
        accordion_high bit,
        accordion_low bit,
        accordion_middle integer,
        color varchar(255),
        line_type varchar(255),
        niente bit,
        number integer,
        spread decimal(12,4),
        value varchar(255),
        line bit,
        sign bit,
        halign varchar(255),
        source varchar(255),
        valign_image varchar(255),
        beater_value varchar(255),
        tip varchar(255),
        justify varchar(255),
        parentheses bit,
        metronome_relation varchar(255),
        principal_voice varchar(255),
        symbol varchar(255),
        placement varchar(255),
        print_object bit,
        stick_material varchar(255),
        stick_type varchar(255),
        end_length decimal(12,4),
        line_end varchar(255),
        size integer,
        direction_type_list_id integer,
        print_style_align_id integer,
        fomatted_text_id integer,
        dashed_formatting_id integer,
        position_id integer,
        text_decoration_id integer,
        print_style_id integer,
        beat_unit_1_id integer,
        beat_unit_2_id integer,
        per_minute_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table direction_type_list (
       id integer not null auto_increment,
        direction_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table directive (
       id integer not null auto_increment,
        lang varchar(255),
        value varchar(255),
        print_style_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table distance (
       id integer not null auto_increment,
        type varchar(255),
        value decimal(12,4),
        appearance_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table dot (
       id integer not null auto_increment,
        placement_id integer,
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table dynamics_marking (
       id integer not null auto_increment,
        dynamics_type varchar(255),
        dynamics_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table editorial (
       id integer not null auto_increment,
        footnote_id integer,
        level_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table editorial_voice (
       id integer not null auto_increment,
        voice varchar(255),
        footnote_id integer,
        level_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table editorial_voice_direction (
       id integer not null auto_increment,
        voice varchar(255),
        footnote_id integer,
        level_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table element_position (
       id integer not null auto_increment,
        element varchar(255),
        position integer,
        primary key (id)
    ) engine=MyISAM;

    create table encoding (
       encoding_type varchar(31) not null,
        id integer not null auto_increment,
        attribute varchar(255),
        element varchar(255),
        type bit,
        value varchar(255),
        software varchar(255),
        encoding_date datetime,
        encoding_description varchar(255),
        encoder_id integer,
        identification_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table ending (
       id integer not null auto_increment,
        end_length decimal(12,4),
        number varchar(255),
        print_object bit,
        text_x decimal(12,4),
        text_y decimal(12,4),
        type varchar(255),
        value varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table feature (
       id integer not null auto_increment,
        type varchar(255),
        value varchar(255),
        grouping_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table figure (
       id integer not null auto_increment,
        extend_id integer,
        figure_number_id integer,
        prefix_id integer,
        suffix_id integer,
        figured_bass_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table first_fret (
       id integer not null auto_increment,
        location varchar(255),
        text varchar(255),
        value integer,
        primary key (id)
    ) engine=MyISAM;

    create table font (
       id integer not null auto_increment,
        font_family varchar(255),
        font_style varchar(255),
        font_weight varchar(255),
        font_size_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table font_size (
       id integer not null auto_increment,
        css_font_size varchar(255),
        font_size decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table formatted_text (
       id integer not null auto_increment,
        value text,
        text_formatting_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table frame (
       id integer not null auto_increment,
        color varchar(255),
        frame_frets integer,
        frame_strings integer,
        halign varchar(255),
        height decimal(12,4),
        unplayed varchar(255),
        valign_image varchar(255),
        width decimal(12,4),
        first_fret_id integer,
        position_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table frame_note (
       id integer not null auto_increment,
        barre_id integer,
        fingering_id integer,
        fret_id integer,
        string_id integer,
        frame_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table full_note (
       id integer not null auto_increment,
        chord bit,
        full_note_type_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table full_note_type (
       full_note_type_name varchar(31) not null,
        id integer not null auto_increment,
        pitch_alter decimal(12,4),
        octave integer,
        step varchar(255),
        display_octave integer,
        display_step varchar(255),
        measure bit,
        primary key (id)
    ) engine=MyISAM;

    create table grace (
       id integer not null auto_increment,
        make_time decimal(12,4),
        slash bit,
        steal_time_following decimal(12,4),
        steal_time_previous decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table group_barline (
       id integer not null auto_increment,
        color varchar(255),
        group_barline_type varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table group_name (
       id integer not null auto_increment,
        group_name varchar(255),
        justify varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table group_symbol (
       id integer not null auto_increment,
        color varchar(255),
        group_symbol_type varchar(255),
        position_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table harmony_chord (
       harmony_chord_type varchar(31) not null,
        id integer not null auto_increment,
        bass_id integer,
        inversion_id integer,
        kind_id integer,
        root_alter_id integer,
        root_step_id integer,
        function_id integer,
        harmony_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table identification (
       id integer not null auto_increment,
        source varchar(255),
        miscellaneous_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table instrument_type (
       instrument_type varchar(31) not null,
        id integer not null auto_increment,
        value integer,
        primary key (id)
    ) engine=MyISAM;

    create table interchangeable (
       id integer not null auto_increment,
        time_separator varchar(255),
        symbol varchar(255),
        time_relation varchar(255),
        time_signature_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table inversion (
       id integer not null auto_increment,
        value integer,
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table key_octave (
       id integer not null auto_increment,
        cancel bit,
        number integer,
        octave integer,
        key_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table key_signature (
       key_type varchar(31) not null,
        id integer not null auto_increment,
        number integer,
        print_object bit,
        fifths integer,
        mode varchar(255),
        print_style_id integer,
        cancel_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table kind (
       id integer not null auto_increment,
        bracket_degrees bit,
        halign varchar(255),
        kind_value varchar(255),
        parentheses_degrees bit,
        stack_degrees bit,
        text varchar(255),
        use_symbols bit,
        valign varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table layout (
       id integer not null auto_increment,
        page_layout_id integer,
        systemm_layout_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table level (
       id integer not null auto_increment,
        reference bit,
        value varchar(255),
        level_display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table level_display (
       id integer not null auto_increment,
        bracket bit,
        parentheses bit,
        size varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table line (
       id integer not null auto_increment,
        line_shape varchar(255),
        line_type varchar(255),
        placement varchar(255),
        dashed_formatting_id integer,
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table line_width (
       id integer not null auto_increment,
        line_width_type varchar(255),
        value decimal(12,4),
        appearance_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table link_attributes (
       id integer not null auto_increment,
        actuate varchar(255),
        href varchar(255),
        link_role varchar(255),
        link_show varchar(255),
        title varchar(255),
        type varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table lyric (
       id integer not null auto_increment,
        color varchar(255),
        end_line bit,
        end_paragraph bit,
        justify varchar(255),
        name varchar(255),
        number varchar(255),
        placement varchar(255),
        print_object bit,
        editorial_id integer,
        lyric_item_id integer,
        position_id integer,
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_font (
       id integer not null auto_increment,
        name varchar(255),
        number varchar(255),
        font_id integer,
        defaults_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_item (
       lyric_item_type varchar(31) not null,
        id integer not null auto_increment,
        type varchar(255),
        print_style_id integer,
        extend_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_language (
       id integer not null auto_increment,
        lang varchar(255),
        name varchar(255),
        number varchar(255),
        defaults_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_syllable (
       id integer not null auto_increment,
        syllabic varchar(255),
        lyric_elision_id integer,
        text_data_id integer,
        lyric_text_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table margins (
       id integer not null auto_increment,
        bottom_margin decimal(12,4),
        left_margin decimal(12,4),
        right_margin decimal(12,4),
        top_margin decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table measure (
       id integer not null auto_increment,
        ordering integer,
        implicit bit,
        non_controlling bit,
        number varchar(255),
        width decimal(12,4),
        part_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table measure_layout (
       id integer not null auto_increment,
        measure_distance decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table measure_style (
       measure_style_type varchar(31) not null,
        id integer not null auto_increment,
        color varchar(255),
        number integer,
        use_symbols bit,
        value integer,
        type varchar(255),
        use_dots bit,
        use_stems bit,
        slashes integer,
        font_id integer,
        slash_group_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table metronome_beam (
       id integer not null auto_increment,
        beam_type varchar(255),
        number integer,
        metronome_note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table metronome_note (
       id integer not null auto_increment,
        metronome_dots integer,
        metronome_type varchar(255),
        metronome_tuplet_id integer,
        note_metronome_2_id integer not null,
        note_metronome_1_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table metronome_tuplet (
       id integer not null auto_increment,
        bracket bit,
        show_number varchar(255),
        type varchar(255),
        time_modification_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table midi_device (
       id integer not null auto_increment,
        midi_device_id varchar(255),
        port integer,
        value varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table midi_instrument (
       id integer not null auto_increment,
        elevation decimal(12,4),
        midi_bank integer,
        midi_channel integer,
        midi_instrumentj_id varchar(255),
        midi_name varchar(255),
        midi_program integer,
        midi_unpitched integer,
        pan decimal(12,4),
        volume decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table miscellaneous (
       id integer not null auto_increment,
        primary key (id)
    ) engine=MyISAM;

    create table miscellaneous_field (
       id integer not null auto_increment,
        name varchar(255),
        value varchar(255),
        miscellaneous_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table music_data (
       music_data_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        duration decimal(12,4),
        coda varchar(255),
        dacapo bit,
        dalsegno varchar(255),
        damper_pedal varchar(255),
        divisions decimal(12,4),
        dynamics decimal(12,4),
        elevation decimal(12,4),
        fine varchar(255),
        forward_repeat bit,
        pan decimal(12,4),
        pizzicato bit,
        segno varchar(255),
        soft_pedal varchar(255),
        sostenuto_pedal varchar(255),
        tempo decimal(12,4),
        time_only varchar(255),
        tocoda varchar(255),
        parentheses bit,
        instruments integer,
        staves integer,
        placement varchar(255),
        print_frame bit,
        print_object bit,
        staff integer,
        type varchar(255),
        location varchar(255),
        blank_page integer,
        measure_numbering_value varchar(255),
        new_page bit,
        new_system bit,
        page_number varchar(255),
        staff_spacing decimal(12,4),
        number varchar(255),
        number_of varchar(255),
        bookmark_id varchar(255),
        name varchar(255),
        attack_length decimal(12,4),
        color varchar(255),
        cue bit,
        end_dynamics decimal(12,4),
        instrument varchar(255),
        release_length decimal(12,4),
        directive bit,
        measure_id integer,
        editorial_id integer,
        offset_id integer,
        print_style_id integer,
        printout_id integer,
        part_symbol_id integer,
        frame_id integer,
        bar_style_id integer,
        coda_print_id integer,
        ending_id integer,
        repeat_id integer,
        segno_print_id integer,
        wavy_line_id integer,
        layout_id integer,
        measure_layout_id integer,
        part_abbreviation_display_id integer,
        part_name_display_id integer,
        print_style_align_id integer,
        credit_display_id integer,
        element_position_id integer,
        editorial_voice_id integer,
        link_attributes_id integer,
        position_id integer,
        accidental_id integer,
        font_id integer,
        full_note_id integer,
        grace_id integer,
        notehead_id integer,
        notehead_text_id integer,
        play_id integer,
        stem_id integer,
        time_modification_id integer,
        type_id integer,
        editorial_voice_direction_id integer,
        sound_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table name_display (
       id integer not null auto_increment,
        print_object bit,
        primary key (id)
    ) engine=MyISAM;

    create table non_traditional_key_type (
       id integer not null auto_increment,
        key_accidental varchar(255),
        key_alter decimal(12,4),
        key_step varchar(255),
        non_traditional_key_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table notation (
       notation_type varchar(31) not null,
        id integer not null auto_increment,
        color varchar(255),
        number integer,
        placement varchar(255),
        type varchar(255),
        print_object bit,
        value varchar(255),
        line_type varchar(255),
        connection_type varchar(255),
        orientation varchar(255),
        fermata_shape varchar(255),
        direction varchar(255),
        bracket bit,
        line_shape varchar(255),
        show_number varchar(255),
        show_type varchar(255),
        accidental_type varchar(255),
        notations_id integer,
        position_id integer,
        print_style_id integer,
        dashed_formatting_id integer,
        bezier_id integer,
        bend_sound_id integer,
        barline_id integer,
        tuplet_actual_id integer,
        tuplet_normal_id integer,
        dynamics_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table notations (
       id integer not null auto_increment,
        print_object bit,
        editorial_id integer,
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table note_size (
       id integer not null auto_increment,
        type varchar(255),
        value decimal(12,4),
        appearance_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table note_type (
       id integer not null auto_increment,
        size varchar(255),
        value varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table notehead (
       id integer not null auto_increment,
        color varchar(255),
        filled bit,
        parentheses bit,
        type varchar(255),
        font_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table notehead_text (
       id integer not null auto_increment,
        primary key (id)
    ) engine=MyISAM;

    create table offset (
       id integer not null auto_increment,
        divisions decimal(12,4),
        sound bit,
        primary key (id)
    ) engine=MyISAM;

    create table ornament (
       ornament_type varchar(31) not null,
        id integer not null auto_increment,
        placement varchar(255),
        slash bit,
        color varchar(255),
        number integer,
        type varchar(255),
        tremolo_marks integer,
        approach varchar(255),
        departure varchar(255),
        long_mordent bit,
        ornaments_id integer,
        placement_text_id integer,
        print_style_id integer,
        trill_sound_id integer,
        position_id integer,
        placement_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table ornament_accidental (
       id integer not null auto_increment,
        accidental_mark_id integer,
        ornaments_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table other_appearance (
       id integer not null auto_increment,
        type varchar(255),
        value varchar(255),
        appearance_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table page_layout (
       id integer not null auto_increment,
        page_height decimal(12,4),
        page_width decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table page_margins (
       id integer not null auto_increment,
        margin_type_key varchar(255),
        type varchar(255),
        margins_id integer,
        page_layout_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table part (
       id integer not null auto_increment,
        ordering integer,
        part_id varchar(255),
        score_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table part_item (
       part_item_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        group_time bit,
        number varchar(255),
        type varchar(255),
        score_part_id varchar(255),
        editorial_id integer,
        group_abbreviation_id integer,
        group_abbreviation_display_id integer,
        group_barline_id integer,
        group_name_id integer,
        group_name_display_id integer,
        group_symbol_id integer,
        identification_id integer,
        part_abbreviation_id integer,
        part_abbreviation_display_id integer,
        part_name_id integer,
        part_name_display_id integer,
        part_list_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table part_list (
       id integer not null auto_increment,
        primary key (id)
    ) engine=MyISAM;

    create table part_name (
       id integer not null auto_increment,
        part_name varchar(255),
        part_name_justify varchar(255),
        part_name_print_object bit,
        part_name_print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table part_symbol (
       id integer not null auto_increment,
        bottom_staff integer,
        color varchar(255),
        group_symbol_type varchar(255),
        top_staff integer,
        position_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table pedal_tuning (
       id integer not null auto_increment,
        pedal_alter decimal(12,4),
        pedal_step varchar(255),
        harp_pedals_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table per_minute (
       id integer not null auto_increment,
        per_minute varchar(255),
        font_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table placement (
       id integer not null auto_increment,
        placement varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table placement_text (
       id integer not null auto_increment,
        placement varchar(255),
        value varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table play (
       id integer not null auto_increment,
        play_id varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table play_type (
       play_type_type varchar(31) not null,
        id integer not null auto_increment,
        value varchar(255),
        type varchar(255),
        play_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table position (
       id integer not null auto_increment,
        default_x decimal(12,4),
        default_y decimal(12,4),
        relative_x decimal(12,4),
        relative_y decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table print_object_style_align (
       id integer not null auto_increment,
        print_object bit,
        print_style_align_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table print_style (
       id integer not null auto_increment,
        color varchar(255),
        font_id integer,
        position_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table print_style_align (
       id integer not null auto_increment,
        halign varchar(255),
        valign varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table printout (
       id integer not null auto_increment,
        print_dot bit,
        print_lyric bit,
        print_object bit,
        print_spacing bit,
        primary key (id)
    ) engine=MyISAM;

    create table root_alter (
       id integer not null auto_increment,
        location varchar(255),
        print_object bit,
        semitones decimal(12,4),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table root_step (
       id integer not null auto_increment,
        step varchar(255),
        text varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table scaling (
       id integer not null auto_increment,
        millimeters decimal(12,4),
        tenths decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table score (
       id integer not null auto_increment,
        score_name varchar(255),
        version varchar(255),
        score_header_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table score_header (
       id integer not null auto_increment,
        movement_number varchar(255),
        movement_title varchar(255),
        defaults_id integer,
        identification_id integer,
        part_list_id integer,
        work_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table score_instrument (
       id integer not null auto_increment,
        instrument_abbreviation varchar(255),
        instrument_name varchar(255),
        instrument_sound varchar(255),
        score_instrument_id varchar(255),
        instrument_type_id integer,
        virtual_instrument_id integer,
        score_part_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table score_midi (
       id integer not null auto_increment,
        midi_device_id integer,
        midi_instrument_id integer,
        score_part_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table score_part_group (
       id integer not null auto_increment,
        group_name varchar(255),
        score_part_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table slash_group (
       id integer not null auto_increment,
        slash_dots integer,
        slash_type varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table sound_midi (
       id integer not null auto_increment,
        midi_device_id integer,
        midi_instrument_id integer,
        play_id integer,
        sound_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table staff_details (
       id integer not null auto_increment,
        capo integer,
        number integer,
        print_object bit,
        print_spacing bit,
        show_frets varchar(255),
        staff_lines integer,
        staff_size decimal(12,4),
        staff_type varchar(255),
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table staff_layout (
       id integer not null auto_increment,
        number integer,
        staff_distance decimal(12,4),
        layout_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table staff_tuning (
       id integer not null auto_increment,
        line integer,
        tuning_id integer,
        staff_details_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table stem (
       id integer not null auto_increment,
        color varchar(255),
        type varchar(255),
        position_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table style_text (
       id integer not null auto_increment,
        value varchar(255),
        print_style_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table system_dividers (
       id integer not null auto_increment,
        left_divider_id integer,
        right_divider_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table system_layout (
       id integer not null auto_increment,
        left_margin decimal(12,4),
        right_margin decimal(12,4),
        system_distance decimal(12,4),
        top_system_distance decimal(12,4),
        system_dividers_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table technical (
       technical_type varchar(31) not null,
        id integer not null auto_increment,
        placement varchar(255),
        string_number integer,
        alternate bit,
        substitution bit,
        value varchar(255),
        number integer,
        type varchar(255),
        color varchar(255),
        arrow_direction varchar(255),
        arrow_style varchar(255),
        circular_arrow varchar(255),
        hole_closed_location varchar(255),
        hole_close_type varchar(255),
        hole_shape varchar(255),
        hole_type varchar(255),
        bend_alter decimal(12,4),
        bend_type varchar(255),
        handbell_type varchar(255),
        harmonic_pitch varchar(255),
        harmonic_type varchar(255),
        print_object bit,
        technicals_id integer,
        print_style_id integer,
        placement_id integer,
        font_id integer,
        placement_text_id integer,
        bend_sound_id integer,
        with_bar_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table text_data (
       id integer not null auto_increment,
        color varchar(255),
        lang varchar(255),
        letter_spacing varchar(255),
        text_direction varchar(255),
        text_rotation decimal(12,4),
        value varchar(255),
        font_id integer,
        text_decoration_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table text_decoration (
       id integer not null auto_increment,
        line_through integer,
        overline integer,
        underline integer,
        primary key (id)
    ) engine=MyISAM;

    create table text_display (
       text_type varchar(31) not null,
        id integer not null auto_increment,
        accidental_type varchar(255),
        name_display_id integer,
        notehead_text_id integer,
        text_formatting_id integer,
        display_text_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table text_font_color (
       id integer not null auto_increment,
        color varchar(255),
        lang varchar(255),
        letter_spacing varchar(255),
        text_direction varchar(255),
        text_rotation decimal(12,4),
        value varchar(255),
        font_id integer,
        text_decoration_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table text_formatting (
       id integer not null auto_increment,
        enclosure varchar(255),
        justify varchar(255),
        lang varchar(255),
        letter_spacing varchar(255),
        line_height varchar(255),
        space varchar(255),
        text_direction varchar(255),
        text_rotation decimal(12,4),
        print_style_align_id integer,
        text_decoration_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table tie (
       id integer not null auto_increment,
        time_only varchar(255),
        type varchar(255),
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table time (
       time_type varchar(31) not null,
        id integer not null auto_increment,
        number integer,
        print_object bit,
        time_separator varchar(255),
        symbol varchar(255),
        value varchar(255),
        print_style_align_id integer,
        interchangeable_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table time_modification (
       id integer not null auto_increment,
        actual_notes integer,
        normal_dots integer,
        normal_notes integer,
        normal_type varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table time_signature_type (
       id integer not null auto_increment,
        beat_type varchar(255),
        beats varchar(255),
        time_signature_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table transpose (
       id integer not null auto_increment,
        chromatic decimal(12,4),
        diatonic integer,
        doubled bit,
        number integer,
        octave_change integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table trill_sound (
       id integer not null auto_increment,
        accelerate bit,
        beats decimal(12,4),
        last_beat decimal(12,4),
        second_beat decimal(12,4),
        start_note varchar(255),
        trill_step varchar(255),
        two_note_turn varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table tuning (
       id integer not null auto_increment,
        tuning_alter decimal(12,4),
        tuning_octave integer,
        tuning_step varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table tuplet_dot (
       id integer not null auto_increment,
        color varchar(255),
        font_id integer,
        tuplet_portion_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table tuplet_number (
       id integer not null auto_increment,
        color varchar(255),
        value integer,
        font_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table tuplet_portion (
       id integer not null auto_increment,
        tuplet_number_id integer,
        tuplet_type_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table tuplet_type (
       id integer not null auto_increment,
        color varchar(255),
        note_type_value varchar(255),
        font_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table typed_text (
       id integer not null auto_increment,
        type varchar(255),
        value varchar(255),
        creator_id integer,
        relation_id integer,
        rights_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table virtual_instrument (
       id integer not null auto_increment,
        virtual_library varchar(255),
        virtual_name varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table work (
       id integer not null auto_increment,
        work_number varchar(255),
        work_title varchar(255),
        opus_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table xml_comment (
       id integer not null auto_increment,
        data varchar(255),
        next_sibling varchar(255),
        parent varchar(255),
        target varchar(255),
        score_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    alter table score 
       add constraint UK3ux9w0rpfpqrljat6wimun1fr unique (score_name);

    alter table accidental 
       add constraint FKf1brlbu5kohjtiexhuaj1uv1j 
       foreign key (level_display_id) 
       references level_display (id);

    alter table accidental 
       add constraint FKhvmx4yrlfgkjklrhf79jpnfox 
       foreign key (print_style_id) 
       references print_style (id);

    alter table accord 
       add constraint FKmallx8sq3vhlhproso60l144t 
       foreign key (tuning_id) 
       references tuning (id);

    alter table accord 
       add constraint FKtr8qmjwj8ddfyk2gi4wmhlm5a 
       foreign key (scordatura_id) 
       references direction_type (id);

    alter table articulation 
       add constraint FKb9visq0pvneglobry068cm9vu 
       foreign key (print_style_id) 
       references print_style (id);

    alter table articulation 
       add constraint FKfc1snbijvkog2rhbpw1on55g5 
       foreign key (placement_id) 
       references placement (id);

    alter table articulation 
       add constraint FK30m8vy3kinx56q1w2xbmp3bty 
       foreign key (line_id) 
       references line (id);

    alter table articulation 
       add constraint FKr6q7jr7cjrtg1eunx02nu9kre 
       foreign key (placement_text_id) 
       references placement_text (id);

    alter table articulation 
       add constraint FK1emxxnei3prqc823q15gt5ccp 
       foreign key (articulations_id) 
       references notation (id);

    alter table bass 
       add constraint FK89b8ei38psnad41dlrveugi8i 
       foreign key (bass_alter_id) 
       references bass_alter (id);

    alter table bass 
       add constraint FKatrignlphdp7f28hwjriexyo0 
       foreign key (bass_step_id) 
       references bass_step (id);

    alter table bass_alter 
       add constraint FKpp4rcwtmq1wrlkx5tfk3vwu8t 
       foreign key (print_style_id) 
       references print_style (id);

    alter table bass_step 
       add constraint FKsiahssnx0jfnglcjp1yl7q66c 
       foreign key (print_style_id) 
       references print_style (id);

    alter table beam 
       add constraint FKbirfijk9ipkvby7y04lbehl9i 
       foreign key (note_id) 
       references music_data (id);

    alter table clef 
       add constraint FK641n5bcx7pl7ts54jp85xym4b 
       foreign key (print_style_id) 
       references print_style (id);

    alter table clef 
       add constraint FKi3ndwd0j6jwnc9b4bs9qrhra8 
       foreign key (attributes_id) 
       references music_data (id);

    alter table credit 
       add constraint FKqt3t8jsqw9f8epk2mg3cbvogs 
       foreign key (score_header_id) 
       references score_header (id);

    alter table credit_display 
       add constraint FKfmotqq7ogiktuy0bgq1y5c2wt 
       foreign key (formatted_text_id) 
       references formatted_text (id);

    alter table credit_display 
       add constraint FKmglmu9us8bs64gvpcg1o713v1 
       foreign key (image_id) 
       references direction_type (id);

    alter table credit_display 
       add constraint FKl8qfo2ls4vq6ydjvqm6gbsexk 
       foreign key (credit_id) 
       references credit (id);

    alter table credit_type 
       add constraint FKrnby6jnu0wbl6k0j5ath1d22v 
       foreign key (credit_id) 
       references credit (id);

    alter table defaults 
       add constraint FKhmw7gcaplpxft7cdmaaxq2uhj 
       foreign key (appearance_id) 
       references appearance (id);

    alter table defaults 
       add constraint FKnk0e1h034e5hlj8uhgig5g10a 
       foreign key (layout_id) 
       references layout (id);

    alter table defaults 
       add constraint FK4n3eyndu2jmqx38rt96qmq5c1 
       foreign key (music_font_id) 
       references font (id);

    alter table defaults 
       add constraint FKpm5m8ghf3bwgx0wwrbgh5r0qq 
       foreign key (defaults_id) 
       references scaling (id);

    alter table defaults 
       add constraint FKj7vt9gdahpixshdx5ca37lw9b 
       foreign key (word_font_id) 
       references font (id);

    alter table degree 
       add constraint FKmt6nydutaow0199jjng5pv50i 
       foreign key (degree_alter_id) 
       references degree_alter (id);

    alter table degree 
       add constraint FK374vyyx5wb58it8ylocotxdo5 
       foreign key (degree_type_id) 
       references degree_type (id);

    alter table degree 
       add constraint FKgsps4nhla3ah77pfpsm32is8e 
       foreign key (degree_value_id) 
       references degree_value (id);

    alter table degree 
       add constraint FKiuti54o5d6w8ryt3sh941yym5 
       foreign key (harmony_chord_id) 
       references harmony_chord (id);

    alter table degree_alter 
       add constraint FKjn6riw3p0qfmbhjsxr4rjsn92 
       foreign key (print_style_id) 
       references print_style (id);

    alter table degree_type 
       add constraint FK9cvlxqwv12nortu2rhfsgefmp 
       foreign key (print_style_id) 
       references print_style (id);

    alter table degree_value 
       add constraint FKpteiuibun6oegnhwkc6bab13s 
       foreign key (print_style_id) 
       references print_style (id);

    alter table direction_type 
       add constraint FKg4bem6dw87egwlbf56tdtr3ae 
       foreign key (direction_type_list_id) 
       references direction_type_list (id);

    alter table direction_type 
       add constraint FKdh2w4ljp8adbmh0wamigdoik5 
       foreign key (print_style_align_id) 
       references print_style_align (id);

    alter table direction_type 
       add constraint FKq9gsdvosrioo2w6yl0374wsps 
       foreign key (fomatted_text_id) 
       references formatted_text (id);

    alter table direction_type 
       add constraint FKgxses1obcssr9l23473nifsgy 
       foreign key (dashed_formatting_id) 
       references dashed_formatting (id);

    alter table direction_type 
       add constraint FKn5f9n6k1d27reulja1lsk0tvj 
       foreign key (position_id) 
       references position (id);

    alter table direction_type 
       add constraint FK5rxl70mdfr2xjhjnvya78c4s9 
       foreign key (text_decoration_id) 
       references text_decoration (id);

    alter table direction_type 
       add constraint FK3gxkoia4ng1lhvkb0y8n69afa 
       foreign key (print_style_id) 
       references print_style (id);

    alter table direction_type 
       add constraint FKh064c4w60nigcc34qthbh5c33 
       foreign key (beat_unit_1_id) 
       references beat_unit (id);

    alter table direction_type 
       add constraint FKgxbebd1i9oygindubiojvhl4i 
       foreign key (beat_unit_2_id) 
       references beat_unit (id);

    alter table direction_type 
       add constraint FK8cyqpdh5f7xrf4bhnos93eee4 
       foreign key (per_minute_id) 
       references per_minute (id);

    alter table direction_type_list 
       add constraint FKblbd4ond4mhyu4o5cd0blqrsm 
       foreign key (direction_id) 
       references music_data (id);

    alter table directive 
       add constraint FKk8ns3fcpr5d516lnprp9o06hd 
       foreign key (print_style_id) 
       references print_style (id);

    alter table directive 
       add constraint FK7ksvq9gvmn531kkcf9yodu3be 
       foreign key (attributes_id) 
       references music_data (id);

    alter table distance 
       add constraint FKh7upf2u0uver0s5p0wbtug9y 
       foreign key (appearance_id) 
       references appearance (id);

    alter table dot 
       add constraint FKhpdcxbtq5p56wcq1fvr3udxes 
       foreign key (placement_id) 
       references placement (id);

    alter table dot 
       add constraint FKinehtcky1m5rv5wexupjmixs 
       foreign key (note_id) 
       references music_data (id);

    alter table dynamics_marking 
       add constraint FKpg0vojk8lfwqhy6wpuo8xy36a 
       foreign key (dynamics_id) 
       references direction_type (id);

    alter table editorial 
       add constraint FK80idprxv3qcwtyg2yvegdreea 
       foreign key (footnote_id) 
       references formatted_text (id);

    alter table editorial 
       add constraint FKtarh2ma91jy07go32cnwywx16 
       foreign key (level_id) 
       references level (id);

    alter table editorial_voice 
       add constraint FK1ustnq8hd3952fjhxmrwjoix0 
       foreign key (footnote_id) 
       references formatted_text (id);

    alter table editorial_voice 
       add constraint FK7oq2hhyjcy1qfe6n0pe9avmkd 
       foreign key (level_id) 
       references level (id);

    alter table editorial_voice_direction 
       add constraint FKcm3f38qna2foj87kxxm2y0icg 
       foreign key (footnote_id) 
       references formatted_text (id);

    alter table editorial_voice_direction 
       add constraint FK51karyxh4hn023p6419t60g0n 
       foreign key (level_id) 
       references level (id);

    alter table encoding 
       add constraint FK8l5bbf29jxhcjw32xffoh6lqe 
       foreign key (encoder_id) 
       references typed_text (id);

    alter table encoding 
       add constraint FKj8g0yqb3iyn82s4pfgrlolqvl 
       foreign key (identification_id) 
       references identification (id);

    alter table ending 
       add constraint FKkonmj86oig30fhaao3drtsm7u 
       foreign key (print_style_id) 
       references print_style (id);

    alter table feature 
       add constraint FKmul6tobnvi4eghf76th7h3mdq 
       foreign key (grouping_id) 
       references music_data (id);

    alter table figure 
       add constraint FK24qvk8c7i5mwab6x26eqsh4a7 
       foreign key (extend_id) 
       references lyric_item (id);

    alter table figure 
       add constraint FKryuf4787pawpnahh3y02ux696 
       foreign key (figure_number_id) 
       references style_text (id);

    alter table figure 
       add constraint FKg1f1l3hcucqy1tq0rnugwkqo4 
       foreign key (prefix_id) 
       references style_text (id);

    alter table figure 
       add constraint FKrn0kj7lltx4ttdghm8l0tyrtd 
       foreign key (suffix_id) 
       references style_text (id);

    alter table figure 
       add constraint FK7nar4w4ybmv0kjnvi9xc78r36 
       foreign key (figured_bass_id) 
       references music_data (id);

    alter table font 
       add constraint FKcm9ljstral5kgbygy0ljqq8sk 
       foreign key (font_size_id) 
       references font_size (id);

    alter table formatted_text 
       add constraint FKno6nplbyevs8p1csp145pfcby 
       foreign key (text_formatting_id) 
       references text_formatting (id);

    alter table frame 
       add constraint FKbu912vqnib5jvntkcd275bsb7 
       foreign key (first_fret_id) 
       references first_fret (id);

    alter table frame 
       add constraint FKch3k2c0mdjgk7g8ce25n2o1xw 
       foreign key (position_id) 
       references position (id);

    alter table frame_note 
       add constraint FKc2bu06jqrnpjaks3lpxhwfkwf 
       foreign key (barre_id) 
       references barre (id);

    alter table frame_note 
       add constraint FK76ygr56dhil0h379d5j4psuvm 
       foreign key (fingering_id) 
       references technical (id);

    alter table frame_note 
       add constraint FKicau0l2kgjcx89dq5xa5n7ntw 
       foreign key (fret_id) 
       references technical (id);

    alter table frame_note 
       add constraint FKa9es26wi46lj5apqkskagihns 
       foreign key (string_id) 
       references technical (id);

    alter table frame_note 
       add constraint FKh021x1kxvt915g5j9n4odf6ew 
       foreign key (frame_id) 
       references frame (id);

    alter table full_note 
       add constraint FKlbq0kh1gpwj47gypadhfgdg7m 
       foreign key (full_note_type_id) 
       references full_note_type (id);

    alter table group_name 
       add constraint FKgklulvowae4d6pkxq8ia8d6dx 
       foreign key (print_style_id) 
       references print_style (id);

    alter table group_symbol 
       add constraint FKjf3mb2qdawomkmetttl00n0ws 
       foreign key (position_id) 
       references position (id);

    alter table harmony_chord 
       add constraint FK29a3948b7puxtfjiwexeku67t 
       foreign key (bass_id) 
       references bass (id);

    alter table harmony_chord 
       add constraint FKrf7vp5xacsmtgo6l21hl76alu 
       foreign key (inversion_id) 
       references inversion (id);

    alter table harmony_chord 
       add constraint FKh2gdlbcwj11qkflxksho57uty 
       foreign key (kind_id) 
       references kind (id);

    alter table harmony_chord 
       add constraint FKj0vumsal06gcidv6av6k8p9tk 
       foreign key (root_alter_id) 
       references root_alter (id);

    alter table harmony_chord 
       add constraint FKdid62dpfcwqx730949k9cmw3m 
       foreign key (root_step_id) 
       references root_step (id);

    alter table harmony_chord 
       add constraint FKmhu4kvcxq786un0fip9976x7p 
       foreign key (function_id) 
       references style_text (id);

    alter table harmony_chord 
       add constraint FK19k6wkl43vtpscei2pbku2wdc 
       foreign key (harmony_id) 
       references music_data (id);

    alter table identification 
       add constraint FK2fo2yc59a55etdbajev56nak7 
       foreign key (miscellaneous_id) 
       references miscellaneous (id);

    alter table interchangeable 
       add constraint FKavstr6ajrdw305ahlh9tivfnn 
       foreign key (time_signature_id) 
       references time (id);

    alter table inversion 
       add constraint FKms7b0og13lb4di6abm1dvg5nx 
       foreign key (print_style_id) 
       references print_style (id);

    alter table key_octave 
       add constraint FKkt0hsurbjdyb1x6jjdfj3j9h6 
       foreign key (key_id) 
       references key_signature (id);

    alter table key_signature 
       add constraint FKd7tumy231x2kcgyodblg3a6du 
       foreign key (print_style_id) 
       references print_style (id);

    alter table key_signature 
       add constraint FK82qm60fbbgff6m3dn8txr0dko 
       foreign key (cancel_id) 
       references cancel (id);

    alter table key_signature 
       add constraint FKddfd76kt7qik3tiygvjx249wx 
       foreign key (attributes_id) 
       references music_data (id);

    alter table kind 
       add constraint FKm66mw2xl9mqj912bx7q3xhjtf 
       foreign key (print_style_id) 
       references print_style (id);

    alter table layout 
       add constraint FK552vkgbeaq1y95tslmngsb7yi 
       foreign key (page_layout_id) 
       references page_layout (id);

    alter table layout 
       add constraint FK7smcened627hb3fwcsewg4gyv 
       foreign key (systemm_layout_id) 
       references system_layout (id);

    alter table level 
       add constraint FKcsxqav3kw8vepoja0kkjc93ul 
       foreign key (level_display_id) 
       references level_display (id);

    alter table line 
       add constraint FKom9rcpsr4cn14tt4k2jarkhg9 
       foreign key (dashed_formatting_id) 
       references dashed_formatting (id);

    alter table line 
       add constraint FKeuni9nyggd7jbfrr98vkssogu 
       foreign key (print_style_id) 
       references print_style (id);

    alter table line_width 
       add constraint FKolk6o737efsh34fe1lbl3646t 
       foreign key (appearance_id) 
       references appearance (id);

    alter table lyric 
       add constraint FKjyeuo5oulnpb4hiid8deb75ot 
       foreign key (editorial_id) 
       references editorial (id);

    alter table lyric 
       add constraint FKog91nc853a53gcxgbvoqaji70 
       foreign key (lyric_item_id) 
       references lyric_item (id);

    alter table lyric 
       add constraint FKpasbsn08ubag7mv6cakyq1k2k 
       foreign key (position_id) 
       references position (id);

    alter table lyric 
       add constraint FK4pb1ar6j9e34u8qgcjq5nsroq 
       foreign key (note_id) 
       references music_data (id);

    alter table lyric_font 
       add constraint FKjbwccxkqmlgtjg5eyif0rwgpk 
       foreign key (font_id) 
       references font (id);

    alter table lyric_font 
       add constraint FKiyxejv2mkqn3755yg4iedo1j0 
       foreign key (defaults_id) 
       references defaults (id);

    alter table lyric_item 
       add constraint FK9a3ar7es2i15r02cc0tgagrva 
       foreign key (print_style_id) 
       references print_style (id);

    alter table lyric_item 
       add constraint FKbjmewleq4j31k1dtppbtcp4dh 
       foreign key (extend_id) 
       references lyric_item (id);

    alter table lyric_language 
       add constraint FKn8jv5f6ji369lamut0desxu0k 
       foreign key (defaults_id) 
       references defaults (id);

    alter table lyric_syllable 
       add constraint FKnbj8a56ent8xvel21vse6qou0 
       foreign key (lyric_elision_id) 
       references text_font_color (id);

    alter table lyric_syllable 
       add constraint FKkqawno7vu5e2hpebgmkvbwme3 
       foreign key (text_data_id) 
       references text_data (id);

    alter table lyric_syllable 
       add constraint FK25xj1xbkynu8c87129s2qodt7 
       foreign key (lyric_text_id) 
       references lyric_item (id);

    alter table measure 
       add constraint FK7okawlbg5nyqon30ll7uavx28 
       foreign key (part_id) 
       references part (id);

    alter table measure_style 
       add constraint FKan3i4e80tqacry684lwp4fvhf 
       foreign key (font_id) 
       references font (id);

    alter table measure_style 
       add constraint FKbj6cxo895qbe3hj8h5xwls7t 
       foreign key (slash_group_id) 
       references slash_group (id);

    alter table measure_style 
       add constraint FKc1nsoxwrw5l5gxv6e0c6oqwob 
       foreign key (attributes_id) 
       references music_data (id);

    alter table metronome_beam 
       add constraint FK7da67iuei7y91g3n7nkd97q9 
       foreign key (metronome_note_id) 
       references metronome_note (id);

    alter table metronome_note 
       add constraint FK40x0x8qv6ynww6qot6dhgss 
       foreign key (metronome_tuplet_id) 
       references metronome_tuplet (id);

    alter table metronome_note 
       add constraint FKr0rle1jd7659157cwj1vqo8yv 
       foreign key (note_metronome_2_id) 
       references direction_type (id);

    alter table metronome_note 
       add constraint FKhngg4ep884s7ppwy919g7wwjs 
       foreign key (note_metronome_1_id) 
       references direction_type (id);

    alter table metronome_tuplet 
       add constraint FK6qhojnpcqb3h0jl7knwj71908 
       foreign key (time_modification_id) 
       references time_modification (id);

    alter table miscellaneous_field 
       add constraint FK3rpl04f3aqqmuymkscglp4yg0 
       foreign key (miscellaneous_id) 
       references miscellaneous (id);

    alter table music_data 
       add constraint FKtgso1241d1sdi5q4weg4982iw 
       foreign key (measure_id) 
       references measure (id);

    alter table music_data 
       add constraint FKi1o1cwuyrqsq5f1s3apa4hf4y 
       foreign key (editorial_id) 
       references editorial (id);

    alter table music_data 
       add constraint FKrm0ydn57ve6lp4prdbph19yca 
       foreign key (offset_id) 
       references offset (id);

    alter table music_data 
       add constraint FK1glg24qa65ycutycdd0skqrkb 
       foreign key (print_style_id) 
       references print_style (id);

    alter table music_data 
       add constraint FKevg7nxdah1nb082n9dl1bivw2 
       foreign key (printout_id) 
       references printout (id);

    alter table music_data 
       add constraint FK7m139gbx76gy3pnnnqkq5saak 
       foreign key (part_symbol_id) 
       references part_symbol (id);

    alter table music_data 
       add constraint FKr6r8ce7ggimnhk0slqpnec9j5 
       foreign key (frame_id) 
       references frame (id);

    alter table music_data 
       add constraint FK90kvf49b2hf9ohcxlbmjo9tcj 
       foreign key (bar_style_id) 
       references bar_style_color (id);

    alter table music_data 
       add constraint FKq9plxquyjpdjmgxlfrxtjajub 
       foreign key (coda_print_id) 
       references print_style_align (id);

    alter table music_data 
       add constraint FK6nxn99o8j8pik2kry92d4xs60 
       foreign key (ending_id) 
       references ending (id);

    alter table music_data 
       add constraint FKn81inqqf1uecp9lrqpoece8pb 
       foreign key (repeat_id) 
       references barline_repeat (id);

    alter table music_data 
       add constraint FKhh4obibkl94v8mlpgfh81ts4k 
       foreign key (segno_print_id) 
       references print_style_align (id);

    alter table music_data 
       add constraint FKig1bj9flp7ebecbgfe2jq8f9c 
       foreign key (wavy_line_id) 
       references ornament (id);

    alter table music_data 
       add constraint FKqlptld1w9lk384q8o76ocxyjc 
       foreign key (layout_id) 
       references layout (id);

    alter table music_data 
       add constraint FKa1ma35y52cpfkiqv11j5fgi8p 
       foreign key (measure_layout_id) 
       references measure_layout (id);

    alter table music_data 
       add constraint FKb1tvu3enlmm3de7txjuvnu3jf 
       foreign key (part_abbreviation_display_id) 
       references name_display (id);

    alter table music_data 
       add constraint FK5ukkyfjov39beuik27uge8dex 
       foreign key (part_name_display_id) 
       references name_display (id);

    alter table music_data 
       add constraint FKntp0ktd4hfsrs8912th1qhefu 
       foreign key (print_style_align_id) 
       references print_style_align (id);

    alter table music_data 
       add constraint FKowu22dl3y19shmrdmjxok999l 
       foreign key (credit_display_id) 
       references credit_display (id);

    alter table music_data 
       add constraint FKs9hdr0f6wuyl1vc4o87odv54w 
       foreign key (element_position_id) 
       references element_position (id);

    alter table music_data 
       add constraint FKhw4wtp4400eh1bn1dwsxce4wm 
       foreign key (editorial_voice_id) 
       references editorial_voice (id);

    alter table music_data 
       add constraint FK8l5ctbd1hibellcrcge7oob41 
       foreign key (link_attributes_id) 
       references link_attributes (id);

    alter table music_data 
       add constraint FKl0qlgpfjb0jha1nexwaps1q79 
       foreign key (position_id) 
       references position (id);

    alter table music_data 
       add constraint FKrbocaap3axrypmkiowm32k1pu 
       foreign key (accidental_id) 
       references accidental (id);

    alter table music_data 
       add constraint FKgpicksq8yhmln32p4k3ujfaxm 
       foreign key (font_id) 
       references font (id);

    alter table music_data 
       add constraint FKbr7vqgo6v4vt89fc0g38m427r 
       foreign key (full_note_id) 
       references full_note (id);

    alter table music_data 
       add constraint FKi7rv04dyy4fqqa33kpdlniiet 
       foreign key (grace_id) 
       references grace (id);

    alter table music_data 
       add constraint FKlgqhcv9mhmdfto1nc6930m13x 
       foreign key (notehead_id) 
       references notehead (id);

    alter table music_data 
       add constraint FKevjnhly5dgfsfx45hltdyy60w 
       foreign key (notehead_text_id) 
       references notehead_text (id);

    alter table music_data 
       add constraint FKqx0mbshxroldy3h0rhnjuen96 
       foreign key (play_id) 
       references play (id);

    alter table music_data 
       add constraint FK26il8bcl7nar7x2q2su94rxil 
       foreign key (stem_id) 
       references stem (id);

    alter table music_data 
       add constraint FKri8bv21rdi21mgj1mbr5rfoi6 
       foreign key (time_modification_id) 
       references time_modification (id);

    alter table music_data 
       add constraint FKuctrlh56grlsxssco02op0ov 
       foreign key (type_id) 
       references note_type (id);

    alter table music_data 
       add constraint FK2i08pgrm0rx7tou1rl64m86h9 
       foreign key (editorial_voice_direction_id) 
       references editorial_voice_direction (id);

    alter table music_data 
       add constraint FKex8und0d9f4odkobxomoq7ia4 
       foreign key (sound_id) 
       references music_data (id);

    alter table non_traditional_key_type 
       add constraint FKq72vcji3in5uw4f22rylm92y 
       foreign key (non_traditional_key_id) 
       references key_signature (id);

    alter table notation 
       add constraint FK5r5gx0kjtp620dora5pb9b4bf 
       foreign key (notations_id) 
       references notations (id);

    alter table notation 
       add constraint FKhw3gdh6pej7kc22xvudakk9xf 
       foreign key (position_id) 
       references position (id);

    alter table notation 
       add constraint FK36e6fcoa8ttpfy0b4vq2vlqoi 
       foreign key (print_style_id) 
       references print_style (id);

    alter table notation 
       add constraint FKmikst8yb146tli0w558f0goly 
       foreign key (dashed_formatting_id) 
       references dashed_formatting (id);

    alter table notation 
       add constraint FKowp9wk2tvekwg5l3i54jpjti4 
       foreign key (bezier_id) 
       references bezier (id);

    alter table notation 
       add constraint FKge2o4tclmalr1xjsh6nlkbh0k 
       foreign key (bend_sound_id) 
       references bend_sound (id);

    alter table notation 
       add constraint FKjeqwla80mk04atqle9daqb3ov 
       foreign key (barline_id) 
       references music_data (id);

    alter table notation 
       add constraint FKql0hwi2lhw9s6bxg21efkac8r 
       foreign key (tuplet_actual_id) 
       references tuplet_portion (id);

    alter table notation 
       add constraint FKkrp8lq0ufd9vdhn3objqvr2e1 
       foreign key (tuplet_normal_id) 
       references tuplet_portion (id);

    alter table notation 
       add constraint FK853o1h22svc12h2hujyq9vsk4 
       foreign key (dynamics_id) 
       references direction_type (id);

    alter table notations 
       add constraint FKg6a7ep34rh5lufsyo8ajv34nk 
       foreign key (editorial_id) 
       references editorial (id);

    alter table notations 
       add constraint FKdpmega5xo8iotvissrrj41pcj 
       foreign key (note_id) 
       references music_data (id);

    alter table note_size 
       add constraint FKm5i756uip4c7k0iy0f4djb0u6 
       foreign key (appearance_id) 
       references appearance (id);

    alter table notehead 
       add constraint FKpkifmgv81yf7xav93tpvccvya 
       foreign key (font_id) 
       references font (id);

    alter table ornament 
       add constraint FKnmqa6fuo6igfi4jv79gh6dwxs 
       foreign key (ornaments_id) 
       references notation (id);

    alter table ornament 
       add constraint FKoy2jywq7553xrg55v0fybhnsr 
       foreign key (placement_text_id) 
       references placement_text (id);

    alter table ornament 
       add constraint FK11qmo7gioqgwq94c8876lgajb 
       foreign key (print_style_id) 
       references print_style (id);

    alter table ornament 
       add constraint FKicpik38a8cvj0yg8ke41uth0n 
       foreign key (trill_sound_id) 
       references trill_sound (id);

    alter table ornament 
       add constraint FKltibfgn7t6c6qp46ro378p7o1 
       foreign key (position_id) 
       references position (id);

    alter table ornament 
       add constraint FK76kucutbhy17dgd30vtfh7ase 
       foreign key (placement_id) 
       references placement (id);

    alter table ornament_accidental 
       add constraint FKqtdjoc60gbgmlnwclvq7buffj 
       foreign key (accidental_mark_id) 
       references notation (id);

    alter table ornament_accidental 
       add constraint FKigbvliujgu40hsgvtaa8cxelg 
       foreign key (ornaments_id) 
       references notation (id);

    alter table other_appearance 
       add constraint FK29ytkk345jy90rk52hlodv2ux 
       foreign key (appearance_id) 
       references appearance (id);

    alter table page_margins 
       add constraint FK6lm9a1utyrw4gd9snues86pr4 
       foreign key (margins_id) 
       references margins (id);

    alter table page_margins 
       add constraint FKk1q1gu5u33a4xiasb9las6s1v 
       foreign key (page_layout_id) 
       references page_layout (id);

    alter table part 
       add constraint FKqqcwcg3r43dy3uad73jatgtws 
       foreign key (score_id) 
       references score (id);

    alter table part_item 
       add constraint FKalucdemf7gtwxkabxehmxcy3d 
       foreign key (editorial_id) 
       references editorial (id);

    alter table part_item 
       add constraint FK9lw931tmgclaam3v9gkwt25fi 
       foreign key (group_abbreviation_id) 
       references group_name (id);

    alter table part_item 
       add constraint FKbovai8x25y2ps84n9lrlcd8nl 
       foreign key (group_abbreviation_display_id) 
       references name_display (id);

    alter table part_item 
       add constraint FKifg0mcwm417gwjf0i8br3j4x5 
       foreign key (group_barline_id) 
       references group_barline (id);

    alter table part_item 
       add constraint FK9768u4iaw2m3k87qrxilq31xh 
       foreign key (group_name_id) 
       references group_name (id);

    alter table part_item 
       add constraint FKcg98fbwsvme2njeruoj528l9d 
       foreign key (group_name_display_id) 
       references name_display (id);

    alter table part_item 
       add constraint FK9ny4awt5k7hbnyk2r8n0gmns7 
       foreign key (group_symbol_id) 
       references group_symbol (id);

    alter table part_item 
       add constraint FK9saxlsbtludhbx3dfne85m8tc 
       foreign key (identification_id) 
       references identification (id);

    alter table part_item 
       add constraint FKeecgjcbomb483vemger9sy0xn 
       foreign key (part_abbreviation_id) 
       references part_name (id);

    alter table part_item 
       add constraint FK9x3js1vy2vxckjb2p6kh6vw7k 
       foreign key (part_abbreviation_display_id) 
       references name_display (id);

    alter table part_item 
       add constraint FKb8wcr8kpm3vow4hciwepak1b0 
       foreign key (part_name_id) 
       references part_name (id);

    alter table part_item 
       add constraint FKwhb7j9b98a73gwwjir309qk4 
       foreign key (part_name_display_id) 
       references name_display (id);

    alter table part_item 
       add constraint FK2i5yk2l02xr0le5h3biidnk29 
       foreign key (part_list_id) 
       references part_list (id);

    alter table part_name 
       add constraint FKk9w82qcy4l6bcwpbwohkhmtg5 
       foreign key (part_name_print_style_id) 
       references print_style (id);

    alter table part_symbol 
       add constraint FK28ea4fghgnp1wxdp88ro9ufd0 
       foreign key (position_id) 
       references position (id);

    alter table pedal_tuning 
       add constraint FKkvm1vni5oi78rbwmadfpcymb8 
       foreign key (harp_pedals_id) 
       references direction_type (id);

    alter table per_minute 
       add constraint FKtmhu8smc4fg2l2wvb2d6p3q1s 
       foreign key (font_id) 
       references font (id);

    alter table placement 
       add constraint FK4ns907staxuc3otbrofrwj1xv 
       foreign key (print_style_id) 
       references print_style (id);

    alter table placement_text 
       add constraint FKqd2x7uux6uwq2dsfrt4tvtt4l 
       foreign key (print_style_id) 
       references print_style (id);

    alter table play_type 
       add constraint FKlqyvck4o1sx2npdvpvi2cr0iq 
       foreign key (play_id) 
       references play (id);

    alter table print_object_style_align 
       add constraint FKemcmcj4t3ac5godq388m91gl6 
       foreign key (print_style_align_id) 
       references print_style_align (id);

    alter table print_style 
       add constraint FKg994nyn3c4wd9xn00qm7wdr7q 
       foreign key (font_id) 
       references font (id);

    alter table print_style 
       add constraint FK74tp99ldhfbp6b1uahk55tg2c 
       foreign key (position_id) 
       references position (id);

    alter table print_style_align 
       add constraint FK88wnduf1il60ole2a5agiu91t 
       foreign key (print_style_id) 
       references print_style (id);

    alter table root_alter 
       add constraint FKma2bv9wu07wrfouuoeft7tmul 
       foreign key (print_style_id) 
       references print_style (id);

    alter table root_step 
       add constraint FK9yripfxgsgrtfm27br0aonfc7 
       foreign key (print_style_id) 
       references print_style (id);

    alter table score 
       add constraint FKibymmlpensin2h24pto4i98l0 
       foreign key (score_header_id) 
       references score_header (id);

    alter table score_header 
       add constraint FKgo0fi29rwnnmp0dg6nim7ed2s 
       foreign key (defaults_id) 
       references defaults (id);

    alter table score_header 
       add constraint FKoe9ya6l782yx8djknyn86th2i 
       foreign key (identification_id) 
       references identification (id);

    alter table score_header 
       add constraint FK6va9w87bq4hdy87wsytra1wfd 
       foreign key (part_list_id) 
       references part_list (id);

    alter table score_header 
       add constraint FK5k1ou1a2c23hh1u41odt6nsj4 
       foreign key (work_id) 
       references work (id);

    alter table score_instrument 
       add constraint FK4y35rg4o1u4687sj7c8e5hx96 
       foreign key (instrument_type_id) 
       references instrument_type (id);

    alter table score_instrument 
       add constraint FK6onkgwv28tgsh2uo01imuawn2 
       foreign key (virtual_instrument_id) 
       references virtual_instrument (id);

    alter table score_instrument 
       add constraint FKcbyc4ag5k50ixryb3cttfavkn 
       foreign key (score_part_id) 
       references part_item (id);

    alter table score_midi 
       add constraint FKcs9atgsafal12ewyjbh65wdx0 
       foreign key (midi_device_id) 
       references midi_device (id);

    alter table score_midi 
       add constraint FK7nfn04do2a3oyf278dgcorfhi 
       foreign key (midi_instrument_id) 
       references midi_instrument (id);

    alter table score_midi 
       add constraint FK65xe1ho5kaux74963negok8vs 
       foreign key (score_part_id) 
       references part_item (id);

    alter table score_part_group 
       add constraint FKq69u1jydxash60pa1hcgvhtnr 
       foreign key (score_part_id) 
       references part_item (id);

    alter table sound_midi 
       add constraint FKr95ayv8tlse4jeyg0xaidll9q 
       foreign key (midi_device_id) 
       references midi_device (id);

    alter table sound_midi 
       add constraint FK1lybxj91pl9dd7esj72dvmubu 
       foreign key (midi_instrument_id) 
       references midi_instrument (id);

    alter table sound_midi 
       add constraint FKbibsj8iumy2p9aghful30nyoa 
       foreign key (play_id) 
       references play (id);

    alter table sound_midi 
       add constraint FK2v6h4if41nww1w81u0xys8nh5 
       foreign key (sound_id) 
       references music_data (id);

    alter table staff_details 
       add constraint FKblmua0qf8os1g3guaj5k9v0pn 
       foreign key (attributes_id) 
       references music_data (id);

    alter table staff_layout 
       add constraint FK7wvfkni379jy12uk833tnd1wm 
       foreign key (layout_id) 
       references layout (id);

    alter table staff_tuning 
       add constraint FKbayw4xdr3fxlk6qhovm3jp2b 
       foreign key (tuning_id) 
       references tuning (id);

    alter table staff_tuning 
       add constraint FKeluy2c31cvumfm0dd9tfmq70u 
       foreign key (staff_details_id) 
       references staff_details (id);

    alter table stem 
       add constraint FKe4y9aepsj1abjlglped2fepyn 
       foreign key (position_id) 
       references position (id);

    alter table style_text 
       add constraint FKkrfn8pxu2pgqiudyof137yedr 
       foreign key (print_style_id) 
       references print_style (id);

    alter table system_dividers 
       add constraint FKs4snu7ps4ci8gc5xqmgwh95je 
       foreign key (left_divider_id) 
       references print_object_style_align (id);

    alter table system_dividers 
       add constraint FKkg34yq81yeow6wevtew79gse9 
       foreign key (right_divider_id) 
       references print_object_style_align (id);

    alter table system_layout 
       add constraint FKoum2iw0o827holjk4avmetxpt 
       foreign key (system_dividers_id) 
       references system_dividers (id);

    alter table technical 
       add constraint FKk1p1j3espd2ej3cfk81elx7qd 
       foreign key (technicals_id) 
       references notation (id);

    alter table technical 
       add constraint FK8weangm16x6rrxt3327f9ehny 
       foreign key (print_style_id) 
       references print_style (id);

    alter table technical 
       add constraint FKtrnbihlvpf714x3m6gegvedkd 
       foreign key (placement_id) 
       references placement (id);

    alter table technical 
       add constraint FK8x6phe95o7ktbmu6wfkp3van0 
       foreign key (font_id) 
       references font (id);

    alter table technical 
       add constraint FKnycbnsdcafannuju6bri39xgm 
       foreign key (placement_text_id) 
       references placement_text (id);

    alter table technical 
       add constraint FKk2qoca6rp76q8da4xyyk8mpug 
       foreign key (bend_sound_id) 
       references bend_sound (id);

    alter table technical 
       add constraint FKm76u1honwnfwuj1u3tx82fooj 
       foreign key (with_bar_id) 
       references placement_text (id);

    alter table text_data 
       add constraint FKe2m2pkl4m1lac5b4ufglvllb2 
       foreign key (font_id) 
       references font (id);

    alter table text_data 
       add constraint FK32ns7tu4ejkkyvir13v9n9m7d 
       foreign key (text_decoration_id) 
       references text_decoration (id);

    alter table text_display 
       add constraint FKewbqxuv4t3j4je18bc13ni6je 
       foreign key (name_display_id) 
       references name_display (id);

    alter table text_display 
       add constraint FKgcm9yg2bcd4ubd3a5dyxef50v 
       foreign key (notehead_text_id) 
       references notehead_text (id);

    alter table text_display 
       add constraint FKa9mybrb3rf4nona3w4a85kxxw 
       foreign key (text_formatting_id) 
       references text_formatting (id);

    alter table text_display 
       add constraint FKa83jcmmr893rnm7fu0m79hspa 
       foreign key (display_text_id) 
       references formatted_text (id);

    alter table text_font_color 
       add constraint FK9s816v01lg2rqcy1l4ad8pyk1 
       foreign key (font_id) 
       references font (id);

    alter table text_font_color 
       add constraint FK1x9nxgxcdytcwfy2yim5sl4rg 
       foreign key (text_decoration_id) 
       references text_decoration (id);

    alter table text_formatting 
       add constraint FKkf85m66nv1fx2xqcf28atirb5 
       foreign key (print_style_align_id) 
       references print_style_align (id);

    alter table text_formatting 
       add constraint FKhdstyqjscdf2v55ewtksqrtx8 
       foreign key (text_decoration_id) 
       references text_decoration (id);

    alter table tie 
       add constraint FKj5k6akoif382o12wegpt952g9 
       foreign key (note_id) 
       references music_data (id);

    alter table time 
       add constraint FKgcs6ifoohhwyyyb0ryyfucbkv 
       foreign key (print_style_align_id) 
       references print_style_align (id);

    alter table time 
       add constraint FK287pjbbbjekxggqk4c9n72oyr 
       foreign key (interchangeable_id) 
       references interchangeable (id);

    alter table time 
       add constraint FKkcgehm6pex2autb9d57hx7aqs 
       foreign key (attributes_id) 
       references music_data (id);

    alter table time_signature_type 
       add constraint FK6m54smq3gfdesiu0t32o6s4wh 
       foreign key (time_signature_id) 
       references time (id);

    alter table transpose 
       add constraint FKmxnumkuq4nsn7wpf9apuyftda 
       foreign key (attributes_id) 
       references music_data (id);

    alter table tuplet_dot 
       add constraint FKkyjpq6x9qk0gn8js5g6jbyur5 
       foreign key (font_id) 
       references font (id);

    alter table tuplet_dot 
       add constraint FK17shk9waf0d00k9f69xprvof8 
       foreign key (tuplet_portion_id) 
       references tuplet_portion (id);

    alter table tuplet_number 
       add constraint FK3m5v7rsxy5wifp2sxnkugtpp4 
       foreign key (font_id) 
       references font (id);

    alter table tuplet_portion 
       add constraint FK7vx5g52oawiuajlnkhv6atbyc 
       foreign key (tuplet_number_id) 
       references tuplet_number (id);

    alter table tuplet_portion 
       add constraint FKtwl390gk0ghoye5fu7tnmyef 
       foreign key (tuplet_type_id) 
       references tuplet_type (id);

    alter table tuplet_type 
       add constraint FK47yv2u1ovwoiuw4jwr9hxoevp 
       foreign key (font_id) 
       references font (id);

    alter table typed_text 
       add constraint FKkug2adi648rr488ym15018bg8 
       foreign key (creator_id) 
       references identification (id);

    alter table typed_text 
       add constraint FK4jv08cd41ebir0ykptwfjm4iq 
       foreign key (relation_id) 
       references identification (id);

    alter table typed_text 
       add constraint FKp1jefy6hfcnocp0p0j2ymgtbf 
       foreign key (rights_id) 
       references identification (id);

    alter table work 
       add constraint FK3tuolhyyg0q9pw6ca6iqyk0at 
       foreign key (opus_id) 
       references link_attributes (id);

    alter table xml_comment 
       add constraint FK3le0xsxpmaagtjiy1625diafq 
       foreign key (score_id) 
       references score (id);
