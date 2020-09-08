
    create table accidental (
       id integer not null auto_increment,
        accidental_type varchar(255),
        cautionary char(1),
        editorial char(1),
        smufl varchar(255),
        display_id integer,
        level_display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table accord (
       id integer not null auto_increment,
        ordering integer,
        string integer,
        tuning_id integer,
        scordatura_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table articulation (
       articulation_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        breath_mark_value varchar(255),
        caesura_value varchar(255),
        type varchar(255),
        smufl varchar(255),
        value varchar(255),
        display_id integer,
        line_id integer,
        articulations_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table barline_ending (
       id integer not null auto_increment,
        end_length decimal(12,4),
        ending_number varchar(255),
        print_object char(1),
        text_x decimal(12,4),
        text_y decimal(12,4),
        ending_type varchar(255),
        value varchar(255),
        display_id integer,
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
        type varchar(255),
        display_id integer,
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
        print_object char(1),
        semitones decimal(12,4),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table bass_step (
       id integer not null auto_increment,
        step varchar(255),
        text varchar(255),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table beam (
       id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        fan varchar(255),
        beam_number integer,
        repeater char(1),
        type varchar(255),
        display_id integer,
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table bend_sound (
       id integer not null auto_increment,
        accelerate char(1),
        beats decimal(12,4),
        first_beat decimal(12,4),
        last_beat decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table bend_with_bar (
       id integer not null auto_increment,
        value varchar(255),
        display_id integer,
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

    create table clef (
       id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        additional char(1),
        after_barline char(1),
        clef_octave_change integer,
        line integer,
        clef_number integer,
        print_object char(1),
        sign varchar(255),
        symbol_size varchar(255),
        display_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table credit (
       id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        page integer,
        score_header_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table credit_display (
       credit_display_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        display_id integer,
        text_format_id integer,
        image_id integer,
        credit_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table credit_type (
       id integer not null auto_increment,
        ordering integer,
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
        scaling_millimeters decimal(12,4),
        scaling_tenths decimal(12,4),
        layout_id integer,
        music_font_id integer,
        word_font_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table degree (
       id integer not null auto_increment,
        ordering integer,
        print_object char(1),
        degree_alter_id integer,
        degree_type_id integer,
        degree_value_id integer,
        harmony_chord_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table degree_alter (
       id integer not null auto_increment,
        plus_minus char(1),
        semitones decimal(12,4),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table degree_type (
       id integer not null auto_increment,
        text varchar(255),
        value varchar(255),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table degree_value (
       id integer not null auto_increment,
        symbol varchar(255),
        text varchar(255),
        value integer,
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table direction_offset (
       id integer not null auto_increment,
        divisions decimal(12,4),
        sound char(1),
        primary key (id)
    ) engine=MyISAM;

    create table direction_type (
       direction_type_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        enclosure varchar(255),
        direction_type varchar(255),
        accordion_high char(1),
        accordion_low char(1),
        accordion_middle integer,
        line_type varchar(255),
        niente char(1),
        direction_type_number integer,
        spread decimal(12,4),
        smufl varchar(255),
        value varchar(255),
        abbreviated char(1),
        line char(1),
        sign char(1),
        height decimal(12,4),
        source varchar(255),
        width decimal(12,4),
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
        end_length decimal(12,4),
        line_end varchar(255),
        direction_type_size integer,
        staff_divide_symbol varchar(255),
        display_id integer,
        text_format_id integer,
        dashed_formatting_id integer,
        metronome_mark_1_id integer,
        metronome_mark_2_id integer,
        direction_type_list_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table direction_type_list (
       id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        direction_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table directive (
       id integer not null auto_increment,
        ordering integer,
        lang varchar(255),
        value varchar(255),
        display_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table display (
       id integer not null auto_increment,
        color varchar(255),
        default_x decimal(12,4),
        default_y decimal(12,4),
        halign varchar(255),
        placement varchar(255),
        relative_x decimal(12,4),
        relative_y decimal(12,4),
        valign varchar(255),
        font_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table distance (
       id integer not null auto_increment,
        ordering integer,
        type varchar(255),
        value decimal(12,4),
        defaults_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table dot (
       id integer not null auto_increment,
        ordering integer,
        display_id integer,
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table dynamics_marking (
       id integer not null auto_increment,
        ordering integer,
        dynamics_type varchar(255),
        smufl varchar(255),
        value varchar(255),
        dynamics_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table editorial (
       id integer not null auto_increment,
        editorial_level_id integer,
        footnote_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table editorial_level (
       id integer not null auto_increment,
        reference char(1),
        value varchar(255),
        level_display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table elision (
       id integer not null auto_increment,
        smufl varchar(255),
        value varchar(255),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table encoding (
       encoding_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        attribute varchar(255),
        element varchar(255),
        supports_type char(1),
        value varchar(255),
        software varchar(255),
        encoding_date datetime,
        encoding_description varchar(255),
        encoder_id integer,
        identification_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table except_voice (
       id integer not null auto_increment,
        ordering integer,
        value varchar(255),
        slash_group_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table feature (
       id integer not null auto_increment,
        ordering integer,
        type varchar(255),
        value varchar(255),
        grouping_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table figure (
       id integer not null auto_increment,
        ordering integer,
        editorial_id integer,
        extend_id integer,
        figure_number_id integer,
        prefix_id integer,
        suffix_id integer,
        figured_bass_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table figure_part (
       id integer not null auto_increment,
        value varchar(255),
        display_id integer,
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
        font_size varchar(255),
        font_style varchar(255),
        font_weight varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table footnote (
       id integer not null auto_increment,
        ordering integer,
        display_id integer,
        text_format_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table frame (
       id integer not null auto_increment,
        element_id varchar(255),
        frame_frets integer,
        frame_strings integer,
        height decimal(12,4),
        unplayed varchar(255),
        width decimal(12,4),
        display_id integer,
        first_fret_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table frame_note (
       id integer not null auto_increment,
        ordering integer,
        barre_id integer,
        fingering_id integer,
        fret_id integer,
        string_id integer,
        frame_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table glyph (
       id integer not null auto_increment,
        ordering integer,
        type varchar(255),
        value varchar(255),
        defaults_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table grace (
       id integer not null auto_increment,
        make_time decimal(12,4),
        slash char(1),
        steal_time_following decimal(12,4),
        steal_time_previous decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table group_barline (
       id integer not null auto_increment,
        group_barline_type varchar(255),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table group_name (
       id integer not null auto_increment,
        group_name varchar(255),
        justify varchar(255),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table group_symbol (
       id integer not null auto_increment,
        group_symbol_type varchar(255),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table harmony_chord (
       harmony_chord_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        value varchar(255),
        bass_id integer,
        inversion_id integer,
        kind_id integer,
        root_alter_id integer,
        root_step_id integer,
        display_id integer,
        harmony_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table identification (
       id integer not null auto_increment,
        source varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table identification_type (
       id integer not null auto_increment,
        ordering integer,
        id_name varchar(255),
        id_type varchar(255),
        id_value varchar(255),
        identification_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table instrument_type (
       instrument_type_type varchar(31) not null,
        id integer not null auto_increment,
        value integer,
        primary key (id)
    ) engine=MyISAM;

    create table interchangeable (
       id integer not null auto_increment,
        time_separator varchar(255),
        symbol varchar(255),
        time_relation varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table inversion (
       id integer not null auto_increment,
        value integer,
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table key_octave (
       id integer not null auto_increment,
        ordering integer,
        cancel char(1),
        key_octave_number integer,
        octave integer,
        key_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table key_signature (
       key_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        key_number integer,
        print_object char(1),
        cancel_fifths integer,
        cancel_location varchar(255),
        fifths integer,
        key_mode varchar(255),
        display_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table kind (
       id integer not null auto_increment,
        bracket_degrees char(1),
        kind_value varchar(255),
        parentheses_degrees char(1),
        stack_degrees char(1),
        text varchar(255),
        use_symbols char(1),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table layout (
       id integer not null auto_increment,
        page_layout_id integer,
        system_layout_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table level_display (
       id integer not null auto_increment,
        bracket char(1),
        parentheses char(1),
        symbol_size varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table line (
       id integer not null auto_increment,
        line_length varchar(255),
        line_shape varchar(255),
        line_type varchar(255),
        dashed_formatting_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table line_width (
       id integer not null auto_increment,
        ordering integer,
        line_width_type varchar(255),
        value decimal(12,4),
        defaults_id integer not null,
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
        ordering integer,
        element_id varchar(255),
        end_line char(1),
        end_paragraph char(1),
        justify varchar(255),
        name varchar(255),
        lyric_number varchar(255),
        print_object char(1),
        time_only varchar(255),
        display_id integer,
        editorial_id integer,
        lyric_item_id integer,
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_font (
       id integer not null auto_increment,
        ordering integer,
        name varchar(255),
        lyric_font_number varchar(255),
        font_id integer,
        defaults_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_item (
       lyric_item_type varchar(31) not null,
        id integer not null auto_increment,
        type varchar(255),
        display_id integer,
        extend_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_language (
       id integer not null auto_increment,
        ordering integer,
        lang varchar(255),
        name varchar(255),
        lyric_language_number varchar(255),
        defaults_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_syllable (
       id integer not null auto_increment,
        ordering integer,
        syllabic varchar(255),
        elision_id integer,
        lyric_text_data_id integer,
        lyric_text_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table lyric_text_data (
       id integer not null auto_increment,
        ordering integer,
        display_id integer,
        text_format_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table measure (
       id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        implicit char(1),
        non_controlling char(1),
        measure_number varchar(255),
        text varchar(255),
        width decimal(12,4),
        part_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table measure_style (
       measure_style_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        measure_style_number integer,
        use_symbols char(1),
        value integer,
        type varchar(255),
        use_dots char(1),
        use_stems char(1),
        slashes integer,
        display_id integer,
        slash_group_id integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table metronome_beam (
       id integer not null auto_increment,
        ordering integer,
        beam_type varchar(255),
        metronome_beam_number integer,
        metronome_note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table metronome_mark (
       metronome_mark_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        per_minute varchar(255),
        beat_unit varchar(255),
        beat_unit_dots integer,
        display_id integer,
        beat_unit_tied_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table metronome_note (
       id integer not null auto_increment,
        ordering integer,
        metronome_dots integer,
        metronome_tied varchar(255),
        metronome_type varchar(255),
        metronome_tuplet_id integer,
        note_metronome_2_id integer not null,
        note_metronome_1_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table metronome_tuplet (
       id integer not null auto_increment,
        bracket char(1),
        show_number varchar(255),
        metronome_tuplet_type varchar(255),
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
        midi_instrument_id varchar(255),
        midi_name varchar(255),
        midi_program integer,
        midi_unpitched integer,
        pan decimal(12,4),
        volume decimal(12,4),
        primary key (id)
    ) engine=MyISAM;

    create table miscellaneous (
       id integer not null auto_increment,
        ordering integer,
        name varchar(255),
        value varchar(255),
        identification_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table music_data (
       music_data_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        duration decimal(12,4),
        element_id varchar(255),
        coda varchar(255),
        dacapo char(1),
        dalsegno varchar(255),
        damper_pedal varchar(255),
        divisions decimal(12,4),
        dynamics decimal(12,4),
        elevation decimal(12,4),
        fine varchar(255),
        forward_repeat char(1),
        pan decimal(12,4),
        pizzicato char(1),
        segno varchar(255),
        soft_pedal varchar(255),
        sostenuto_pedal varchar(255),
        tempo decimal(12,4),
        time_only varchar(255),
        tocoda varchar(255),
        parentheses char(1),
        instruments integer,
        staves integer,
        print_frame char(1),
        print_object char(1),
        staff integer,
        type varchar(255),
        bar_style varchar(255),
        barline_location varchar(255),
        blank_page integer,
        measure_distance decimal(12,4),
        measure_numbering_value varchar(255),
        new_page char(1),
        new_system char(1),
        page_number varchar(255),
        staff_spacing decimal(12,4),
        grouping_number varchar(255),
        number_of varchar(255),
        bookmark_id varchar(255),
        element varchar(255),
        name varchar(255),
        position integer,
        voice varchar(255),
        attack_length decimal(12,4),
        chord char(1),
        cue char(1),
        end_dynamics decimal(12,4),
        instrument varchar(255),
        note_size varchar(255),
        note_value varchar(255),
        print_leger char(1),
        release_length decimal(12,4),
        directive char(1),
        measure_id integer,
        editorial_id integer,
        offset_id integer,
        display_id integer,
        printout_id integer,
        part_symbol_id integer,
        frame_id integer,
        coda_id integer,
        barline_ending_id integer,
        barline_repeat_id integer,
        segno_id integer,
        wavy_line_id integer,
        layout_id integer,
        part_abbreviation_display_id integer,
        part_name_display_id integer,
        link_attributes_id integer,
        accidental_id integer,
        grace_id integer,
        note_type_id integer,
        notehead_id integer,
        play_id integer,
        stem_id integer,
        time_modification_id integer,
        sound_id integer,
        credit_display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table name_display (
       id integer not null auto_increment,
        print_object char(1),
        primary key (id)
    ) engine=MyISAM;

    create table non_traditional_key_type (
       id integer not null auto_increment,
        ordering integer,
        key_accidental varchar(255),
        key_accidental_smufl varchar(255),
        key_alter decimal(12,4),
        key_step varchar(255),
        key_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table notation (
       notation_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        notation_number integer,
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
        display_id integer,
        dashed_formatting_id integer,
        bezier_id integer,
        bend_sound_id integer,
        tuplet_actual_id integer,
        tuplet_normal_id integer,
        dynamics_id integer,
        level_display_id integer,
        barline_id integer,
        ornaments_id integer,
        notations_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table notations (
       id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        print_object char(1),
        editorial_id integer,
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table note_size (
       id integer not null auto_increment,
        ordering integer,
        type varchar(255),
        value decimal(12,4),
        defaults_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table note_type (
       note_type_type varchar(31) not null,
        id integer not null auto_increment,
        pitch_alter decimal(12,4),
        octave integer,
        step varchar(255),
        measure char(1),
        primary key (id)
    ) engine=MyISAM;

    create table notehead (
       id integer not null auto_increment,
        filled char(1),
        parentheses char(1),
        smufl varchar(255),
        type varchar(255),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table ornament (
       ornament_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        smufl varchar(255),
        value varchar(255),
        slash char(1),
        wavy_line_number integer,
        type varchar(255),
        tremolo_marks integer,
        approach varchar(255),
        departure varchar(255),
        long_mordent char(1),
        display_id integer,
        trill_sound_id integer,
        ornaments_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table other_appearance (
       id integer not null auto_increment,
        ordering integer,
        type varchar(255),
        value varchar(255),
        defaults_id integer not null,
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
        bottom_margin decimal(12,4),
        left_margin decimal(12,4),
        right_margin decimal(12,4),
        top_margin decimal(12,4),
        type varchar(255),
        page_layout_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table part (
       id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        part_id varchar(255),
        score_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table part_list_item (
       part_list_item_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        group_time char(1),
        part_group_number varchar(255),
        part_group_type varchar(255),
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
        score_header_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table part_name (
       id integer not null auto_increment,
        justify varchar(255),
        part_name varchar(255),
        print_object char(1),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table part_symbol (
       id integer not null auto_increment,
        bottom_staff integer,
        group_symbol_type varchar(255),
        top_staff integer,
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table pedal_tuning (
       id integer not null auto_increment,
        ordering integer,
        pedal_alter decimal(12,4),
        pedal_step varchar(255),
        harp_pedals_id integer not null,
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
        ordering integer,
        value varchar(255),
        type varchar(255),
        play_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table printout (
       id integer not null auto_increment,
        print_dot char(1),
        print_lyric char(1),
        print_object char(1),
        print_spacing char(1),
        primary key (id)
    ) engine=MyISAM;

    create table root_alter (
       id integer not null auto_increment,
        location varchar(255),
        print_object char(1),
        semitones decimal(12,4),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table root_step (
       id integer not null auto_increment,
        step varchar(255),
        text varchar(255),
        display_id integer,
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
        work_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table score_instrument (
       id integer not null auto_increment,
        ordering integer,
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
        ordering integer,
        midi_device_id integer,
        midi_instrument_id integer,
        score_part_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table score_part_group (
       id integer not null auto_increment,
        ordering integer,
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
        ordering integer,
        midi_device_id integer,
        midi_instrument_id integer,
        play_id integer,
        sound_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table staff_details (
       id integer not null auto_increment,
        ordering integer,
        capo integer,
        staff_details_number integer,
        print_object char(1),
        print_spacing char(1),
        show_frets varchar(255),
        staff_lines integer,
        staff_size decimal(12,4),
        staff_type varchar(255),
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table staff_layout (
       id integer not null auto_increment,
        ordering integer,
        staff_layout_number integer,
        staff_distance decimal(12,4),
        layout_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table staff_tuning (
       id integer not null auto_increment,
        ordering integer,
        line integer,
        tuning_id integer,
        staff_details_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table stem (
       id integer not null auto_increment,
        type varchar(255),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table system_divider (
       id integer not null auto_increment,
        print_object char(1),
        display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table system_layout (
       id integer not null auto_increment,
        left_margin decimal(12,4),
        right_margin decimal(12,4),
        system_distance decimal(12,4),
        top_system_distance decimal(12,4),
        left_system_divider_id integer,
        right_system_divider_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table technical (
       technical_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        string_number integer,
        alternate char(1),
        substitution char(1),
        value varchar(255),
        notation_number integer,
        notation_type varchar(255),
        location varchar(255),
        smufl varchar(255),
        arrow_direction varchar(255),
        arrow_style varchar(255),
        arrowhead char(1),
        circular_arrow varchar(255),
        hole_closed_location varchar(255),
        hole_closed_type varchar(255),
        hole_shape varchar(255),
        hole_type varchar(255),
        tap_hand varchar(255),
        bend_alter decimal(12,4),
        bend_type varchar(255),
        handbell_type varchar(255),
        harmonic_pitch varchar(255),
        harmonic_type varchar(255),
        print_object char(1),
        display_id integer,
        bend_sound_id integer,
        with_bar_id integer,
        technicals_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table text_display (
       text_display_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        accidental_type varchar(255),
        smufl varchar(255),
        display_id integer,
        text_format_id integer,
        note_id integer,
        name_display_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table text_format (
       id integer not null auto_increment,
        enclosure varchar(255),
        justify varchar(255),
        lang varchar(255),
        letter_spacing varchar(255),
        line_height varchar(255),
        line_through integer,
        overline integer,
        space varchar(255),
        text_direction varchar(255),
        text_rotation decimal(12,4),
        underline integer,
        value varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table tie (
       id integer not null auto_increment,
        ordering integer,
        time_only varchar(255),
        type varchar(255),
        note_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table time (
       time_type varchar(31) not null,
        id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        time_number integer,
        print_object char(1),
        time_separator varchar(255),
        symbol varchar(255),
        value varchar(255),
        display_id integer,
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
        ordering integer,
        beat_type varchar(255),
        beats varchar(255),
        time_signature_id integer,
        interchangeable_id integer,
        primary key (id)
    ) engine=MyISAM;

    create table transpose (
       id integer not null auto_increment,
        ordering integer,
        element_id varchar(255),
        chromatic decimal(12,4),
        diatonic integer,
        doubled char(1),
        staff_number integer,
        octave_change integer,
        attributes_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table trill_sound (
       id integer not null auto_increment,
        accelerate char(1),
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
        ordering integer,
        display_id integer,
        tuplet_portion_id integer not null,
        primary key (id)
    ) engine=MyISAM;

    create table tuplet_number (
       id integer not null auto_increment,
        value integer,
        display_id integer,
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
        note_type_value varchar(255),
        display_id integer,
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
       add constraint FKog7p17vaochq32sjs8bjxv1v4 
       foreign key (display_id) 
       references display (id);

    alter table accidental 
       add constraint FKf1brlbu5kohjtiexhuaj1uv1j 
       foreign key (level_display_id) 
       references level_display (id);

    alter table accord 
       add constraint FKmallx8sq3vhlhproso60l144t 
       foreign key (tuning_id) 
       references tuning (id);

    alter table accord 
       add constraint FKtr8qmjwj8ddfyk2gi4wmhlm5a 
       foreign key (scordatura_id) 
       references direction_type (id);

    alter table articulation 
       add constraint FKnn2i78j4t94y2sch4dxc9bj4x 
       foreign key (display_id) 
       references display (id);

    alter table articulation 
       add constraint FK30m8vy3kinx56q1w2xbmp3bty 
       foreign key (line_id) 
       references line (id);

    alter table articulation 
       add constraint FK1emxxnei3prqc823q15gt5ccp 
       foreign key (articulations_id) 
       references notation (id);

    alter table barline_ending 
       add constraint FKmkus70yu4xa3xm09eke0ch7jh 
       foreign key (display_id) 
       references display (id);

    alter table barre 
       add constraint FKfympa43emgp0kyy1qqbrtqnow 
       foreign key (display_id) 
       references display (id);

    alter table bass 
       add constraint FK89b8ei38psnad41dlrveugi8i 
       foreign key (bass_alter_id) 
       references bass_alter (id);

    alter table bass 
       add constraint FKatrignlphdp7f28hwjriexyo0 
       foreign key (bass_step_id) 
       references bass_step (id);

    alter table bass_alter 
       add constraint FK2ty4w07l9qp4tse2qqdf2u68s 
       foreign key (display_id) 
       references display (id);

    alter table bass_step 
       add constraint FKptfr45ox5vrmlw4pa2vbf2apu 
       foreign key (display_id) 
       references display (id);

    alter table beam 
       add constraint FK3r7dqpis0rakfenlmnbtdvs4 
       foreign key (display_id) 
       references display (id);

    alter table beam 
       add constraint FKbirfijk9ipkvby7y04lbehl9i 
       foreign key (note_id) 
       references music_data (id);

    alter table bend_with_bar 
       add constraint FKmpp6255g41g09vgbu7edv7xqq 
       foreign key (display_id) 
       references display (id);

    alter table clef 
       add constraint FKa378tnca4yd6yylyai6yl86uf 
       foreign key (display_id) 
       references display (id);

    alter table clef 
       add constraint FKi3ndwd0j6jwnc9b4bs9qrhra8 
       foreign key (attributes_id) 
       references music_data (id);

    alter table credit 
       add constraint FKqt3t8jsqw9f8epk2mg3cbvogs 
       foreign key (score_header_id) 
       references score_header (id);

    alter table credit_display 
       add constraint FKlyds4j445i0ws1sv85ocihbmp 
       foreign key (display_id) 
       references display (id);

    alter table credit_display 
       add constraint FKlc9mqx1m5cix2cr4vt6p1at09 
       foreign key (text_format_id) 
       references text_format (id);

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
       add constraint FKnk0e1h034e5hlj8uhgig5g10a 
       foreign key (layout_id) 
       references layout (id);

    alter table defaults 
       add constraint FK4n3eyndu2jmqx38rt96qmq5c1 
       foreign key (music_font_id) 
       references font (id);

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
       add constraint FKln9u0gr6hmcmb9tp1e401v7gq 
       foreign key (display_id) 
       references display (id);

    alter table degree_type 
       add constraint FKt02dg5fdo8fu2fcmy42216mnb 
       foreign key (display_id) 
       references display (id);

    alter table degree_value 
       add constraint FKhddv8q1fky1rcpq7b78200yd2 
       foreign key (display_id) 
       references display (id);

    alter table direction_type 
       add constraint FK77ibcdkbyqisesln0qu89w1k9 
       foreign key (display_id) 
       references display (id);

    alter table direction_type 
       add constraint FK8aw4acynt9n77ipwwgwppdp9s 
       foreign key (text_format_id) 
       references text_format (id);

    alter table direction_type 
       add constraint FKgxses1obcssr9l23473nifsgy 
       foreign key (dashed_formatting_id) 
       references dashed_formatting (id);

    alter table direction_type 
       add constraint FKsxbv146gynnqnw6nab75n1ba9 
       foreign key (metronome_mark_1_id) 
       references metronome_mark (id);

    alter table direction_type 
       add constraint FKesly754x1a2cyws3u2mrwmrym 
       foreign key (metronome_mark_2_id) 
       references metronome_mark (id);

    alter table direction_type 
       add constraint FKg4bem6dw87egwlbf56tdtr3ae 
       foreign key (direction_type_list_id) 
       references direction_type_list (id);

    alter table direction_type_list 
       add constraint FKblbd4ond4mhyu4o5cd0blqrsm 
       foreign key (direction_id) 
       references music_data (id);

    alter table directive 
       add constraint FKolp4d7yqveg72d8nhbmp3kx8r 
       foreign key (display_id) 
       references display (id);

    alter table directive 
       add constraint FK7ksvq9gvmn531kkcf9yodu3be 
       foreign key (attributes_id) 
       references music_data (id);

    alter table display 
       add constraint FKojhuhxyhklq2hjisna3o24n1v 
       foreign key (font_id) 
       references font (id);

    alter table distance 
       add constraint FKsgkg2m74unfcqfelw001vgnkm 
       foreign key (defaults_id) 
       references defaults (id);

    alter table dot 
       add constraint FKj1btpaoyc9nvstpp5k8f06lj8 
       foreign key (display_id) 
       references display (id);

    alter table dot 
       add constraint FKinehtcky1m5rv5wexupjmixs 
       foreign key (note_id) 
       references music_data (id);

    alter table dynamics_marking 
       add constraint FKpg0vojk8lfwqhy6wpuo8xy36a 
       foreign key (dynamics_id) 
       references direction_type (id);

    alter table editorial 
       add constraint FKt3v43sax3kgne3hjrfvtth5f9 
       foreign key (editorial_level_id) 
       references editorial_level (id);

    alter table editorial 
       add constraint FKgd6ji3ihwlf2njs9w5gl6b550 
       foreign key (footnote_id) 
       references footnote (id);

    alter table editorial_level 
       add constraint FKjrho9g6v6tm3i5ervhove93oe 
       foreign key (level_display_id) 
       references level_display (id);

    alter table elision 
       add constraint FKm7ixjbc10372o8tbtxfyp7dec 
       foreign key (display_id) 
       references display (id);

    alter table encoding 
       add constraint FKbght4fxs3rpny6i4fe9x8mlo6 
       foreign key (encoder_id) 
       references identification_type (id);

    alter table encoding 
       add constraint FKj8g0yqb3iyn82s4pfgrlolqvl 
       foreign key (identification_id) 
       references identification (id);

    alter table except_voice 
       add constraint FKnm4i9hxd6b7xb19cqiywqya2e 
       foreign key (slash_group_id) 
       references slash_group (id);

    alter table feature 
       add constraint FKmul6tobnvi4eghf76th7h3mdq 
       foreign key (grouping_id) 
       references music_data (id);

    alter table figure 
       add constraint FK3t1vsii1hkcl4ool8vfnkwtug 
       foreign key (editorial_id) 
       references editorial (id);

    alter table figure 
       add constraint FK24qvk8c7i5mwab6x26eqsh4a7 
       foreign key (extend_id) 
       references lyric_item (id);

    alter table figure 
       add constraint FK6l2ti6mtpl8t7mxsltpevb1tf 
       foreign key (figure_number_id) 
       references figure_part (id);

    alter table figure 
       add constraint FK6oc8s84xmcioxn4iyy21n4qnc 
       foreign key (prefix_id) 
       references figure_part (id);

    alter table figure 
       add constraint FK26ov05q2kbutwjpnfot333dla 
       foreign key (suffix_id) 
       references figure_part (id);

    alter table figure 
       add constraint FK7nar4w4ybmv0kjnvi9xc78r36 
       foreign key (figured_bass_id) 
       references music_data (id);

    alter table figure_part 
       add constraint FKhpc3q59ub4085gugob9osxwd 
       foreign key (display_id) 
       references display (id);

    alter table footnote 
       add constraint FKojw33aa3lklit05wgqekf3yxw 
       foreign key (display_id) 
       references display (id);

    alter table footnote 
       add constraint FKo822s5f1w4n3alf5oe205qbnj 
       foreign key (text_format_id) 
       references text_format (id);

    alter table frame 
       add constraint FKg86hx52tr4n9nbl5bxjnqyw00 
       foreign key (display_id) 
       references display (id);

    alter table frame 
       add constraint FKbu912vqnib5jvntkcd275bsb7 
       foreign key (first_fret_id) 
       references first_fret (id);

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

    alter table glyph 
       add constraint FK5ks4jwnpnle76p49yo2vgsyjw 
       foreign key (defaults_id) 
       references defaults (id);

    alter table group_barline 
       add constraint FKfig7m4m884wx19gesoojlgtg3 
       foreign key (display_id) 
       references display (id);

    alter table group_name 
       add constraint FKdpc0x3kr0ntfsiqe03eiia154 
       foreign key (display_id) 
       references display (id);

    alter table group_symbol 
       add constraint FK3vsrxsapgrc630v5w13nrv739 
       foreign key (display_id) 
       references display (id);

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
       add constraint FK75mm52mh18sryng7tvg559qsi 
       foreign key (display_id) 
       references display (id);

    alter table harmony_chord 
       add constraint FK19k6wkl43vtpscei2pbku2wdc 
       foreign key (harmony_id) 
       references music_data (id);

    alter table identification_type 
       add constraint FK8dm2m32nujxu57i8veecs1o5t 
       foreign key (identification_id) 
       references identification (id);

    alter table inversion 
       add constraint FK8wmswbqbiahcr1tbssadhs3d6 
       foreign key (display_id) 
       references display (id);

    alter table key_octave 
       add constraint FKkt0hsurbjdyb1x6jjdfj3j9h6 
       foreign key (key_id) 
       references key_signature (id);

    alter table key_signature 
       add constraint FKcbiju7t2tfmedojiu5hgnoup4 
       foreign key (display_id) 
       references display (id);

    alter table key_signature 
       add constraint FKddfd76kt7qik3tiygvjx249wx 
       foreign key (attributes_id) 
       references music_data (id);

    alter table kind 
       add constraint FKbqjf1wkdh2ki6thj3fvqivpv 
       foreign key (display_id) 
       references display (id);

    alter table layout 
       add constraint FK552vkgbeaq1y95tslmngsb7yi 
       foreign key (page_layout_id) 
       references page_layout (id);

    alter table layout 
       add constraint FK5j4eivsgxi1902g4inugxxyiu 
       foreign key (system_layout_id) 
       references system_layout (id);

    alter table line 
       add constraint FKom9rcpsr4cn14tt4k2jarkhg9 
       foreign key (dashed_formatting_id) 
       references dashed_formatting (id);

    alter table line_width 
       add constraint FKnm9ykrmaj7ne7udju5pkqh711 
       foreign key (defaults_id) 
       references defaults (id);

    alter table lyric 
       add constraint FK3wlvu2d93tldinxvxu9omcly2 
       foreign key (display_id) 
       references display (id);

    alter table lyric 
       add constraint FKjyeuo5oulnpb4hiid8deb75ot 
       foreign key (editorial_id) 
       references editorial (id);

    alter table lyric 
       add constraint FKog91nc853a53gcxgbvoqaji70 
       foreign key (lyric_item_id) 
       references lyric_item (id);

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
       add constraint FK6gdrh9hd83f38euwdfx1nggul 
       foreign key (display_id) 
       references display (id);

    alter table lyric_item 
       add constraint FKbjmewleq4j31k1dtppbtcp4dh 
       foreign key (extend_id) 
       references lyric_item (id);

    alter table lyric_language 
       add constraint FKn8jv5f6ji369lamut0desxu0k 
       foreign key (defaults_id) 
       references defaults (id);

    alter table lyric_syllable 
       add constraint FKlmk9aphu2dxt9l8h0c4wkgrso 
       foreign key (elision_id) 
       references elision (id);

    alter table lyric_syllable 
       add constraint FKtm9e8rxdx69n6w4nk8607ulx3 
       foreign key (lyric_text_data_id) 
       references lyric_text_data (id);

    alter table lyric_syllable 
       add constraint FK25xj1xbkynu8c87129s2qodt7 
       foreign key (lyric_text_id) 
       references lyric_item (id);

    alter table lyric_text_data 
       add constraint FKmc2stvngctieoh7j6k407j70v 
       foreign key (display_id) 
       references display (id);

    alter table lyric_text_data 
       add constraint FKfw1oy1qgsq3mk72oitwh18r4v 
       foreign key (text_format_id) 
       references text_format (id);

    alter table measure 
       add constraint FK7okawlbg5nyqon30ll7uavx28 
       foreign key (part_id) 
       references part (id);

    alter table measure_style 
       add constraint FKj3lm680yi0h9ikyq1w4uljq1v 
       foreign key (display_id) 
       references display (id);

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

    alter table metronome_mark 
       add constraint FKqfv6oplc70lkwvhimrio2f5vi 
       foreign key (display_id) 
       references display (id);

    alter table metronome_mark 
       add constraint FKydiwjgssa8pl1876nebaqob9 
       foreign key (beat_unit_tied_id) 
       references metronome_mark (id);

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

    alter table miscellaneous 
       add constraint FKaka7w5pn26y9mnfj3dnj1kj22 
       foreign key (identification_id) 
       references identification (id);

    alter table music_data 
       add constraint FKtgso1241d1sdi5q4weg4982iw 
       foreign key (measure_id) 
       references measure (id);

    alter table music_data 
       add constraint FKi1o1cwuyrqsq5f1s3apa4hf4y 
       foreign key (editorial_id) 
       references editorial (id);

    alter table music_data 
       add constraint FKsfyj9m6igtt1bktnjrb9e7h63 
       foreign key (offset_id) 
       references direction_offset (id);

    alter table music_data 
       add constraint FKk94ghor1mdy12w2m5eis6g1vj 
       foreign key (display_id) 
       references display (id);

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
       add constraint FK74wouy3pwlsp1e3l1rpjs4wer 
       foreign key (coda_id) 
       references direction_type (id);

    alter table music_data 
       add constraint FK1xuh8o8y3j2yly2pq0731gfi0 
       foreign key (barline_ending_id) 
       references barline_ending (id);

    alter table music_data 
       add constraint FKk45cufpuj53k5dnrp549yyilq 
       foreign key (barline_repeat_id) 
       references barline_repeat (id);

    alter table music_data 
       add constraint FKsdkthf201p0rbpcgkt00k0efe 
       foreign key (segno_id) 
       references direction_type (id);

    alter table music_data 
       add constraint FKig1bj9flp7ebecbgfe2jq8f9c 
       foreign key (wavy_line_id) 
       references ornament (id);

    alter table music_data 
       add constraint FKqlptld1w9lk384q8o76ocxyjc 
       foreign key (layout_id) 
       references layout (id);

    alter table music_data 
       add constraint FKb1tvu3enlmm3de7txjuvnu3jf 
       foreign key (part_abbreviation_display_id) 
       references name_display (id);

    alter table music_data 
       add constraint FK5ukkyfjov39beuik27uge8dex 
       foreign key (part_name_display_id) 
       references name_display (id);

    alter table music_data 
       add constraint FK8l5ctbd1hibellcrcge7oob41 
       foreign key (link_attributes_id) 
       references link_attributes (id);

    alter table music_data 
       add constraint FKrbocaap3axrypmkiowm32k1pu 
       foreign key (accidental_id) 
       references accidental (id);

    alter table music_data 
       add constraint FKi7rv04dyy4fqqa33kpdlniiet 
       foreign key (grace_id) 
       references grace (id);

    alter table music_data 
       add constraint FK304olx85kcdy9jpoifffe2xcu 
       foreign key (note_type_id) 
       references note_type (id);

    alter table music_data 
       add constraint FKlgqhcv9mhmdfto1nc6930m13x 
       foreign key (notehead_id) 
       references notehead (id);

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
       add constraint FKex8und0d9f4odkobxomoq7ia4 
       foreign key (sound_id) 
       references music_data (id);

    alter table music_data 
       add constraint FKowu22dl3y19shmrdmjxok999l 
       foreign key (credit_display_id) 
       references credit_display (id);

    alter table non_traditional_key_type 
       add constraint FK2p06rp4qn3esdyyxwy1qrfyxc 
       foreign key (key_id) 
       references key_signature (id);

    alter table notation 
       add constraint FK5j2otsml2aexns8nkdp708hys 
       foreign key (display_id) 
       references display (id);

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

    alter table notation 
       add constraint FKtna2ume78xg3ua7mnvb4r2my0 
       foreign key (level_display_id) 
       references level_display (id);

    alter table notation 
       add constraint FKjeqwla80mk04atqle9daqb3ov 
       foreign key (barline_id) 
       references music_data (id);

    alter table notation 
       add constraint FKej9qdi0k1jternmlnasa57ia0 
       foreign key (ornaments_id) 
       references notation (id);

    alter table notation 
       add constraint FK5r5gx0kjtp620dora5pb9b4bf 
       foreign key (notations_id) 
       references notations (id);

    alter table notations 
       add constraint FKg6a7ep34rh5lufsyo8ajv34nk 
       foreign key (editorial_id) 
       references editorial (id);

    alter table notations 
       add constraint FKdpmega5xo8iotvissrrj41pcj 
       foreign key (note_id) 
       references music_data (id);

    alter table note_size 
       add constraint FKleil8snl2e6g62vbf47xe50bd 
       foreign key (defaults_id) 
       references defaults (id);

    alter table notehead 
       add constraint FKr01qgxcl50q2q6jlxpr70msij 
       foreign key (display_id) 
       references display (id);

    alter table ornament 
       add constraint FK2yyq8ugyye0en8tmbswevkylp 
       foreign key (display_id) 
       references display (id);

    alter table ornament 
       add constraint FKicpik38a8cvj0yg8ke41uth0n 
       foreign key (trill_sound_id) 
       references trill_sound (id);

    alter table ornament 
       add constraint FKnmqa6fuo6igfi4jv79gh6dwxs 
       foreign key (ornaments_id) 
       references notation (id);

    alter table other_appearance 
       add constraint FKe2844y956ch1vqlvhuq718eeo 
       foreign key (defaults_id) 
       references defaults (id);

    alter table page_margins 
       add constraint FKk1q1gu5u33a4xiasb9las6s1v 
       foreign key (page_layout_id) 
       references page_layout (id);

    alter table part 
       add constraint FKqqcwcg3r43dy3uad73jatgtws 
       foreign key (score_id) 
       references score (id);

    alter table part_list_item 
       add constraint FK4eo8dqyxosbwnt3jiwwvx314g 
       foreign key (editorial_id) 
       references editorial (id);

    alter table part_list_item 
       add constraint FKt278pnohp6vv89honw7jse266 
       foreign key (group_abbreviation_id) 
       references group_name (id);

    alter table part_list_item 
       add constraint FK8uoyb6n01yikv0f0ntth464jf 
       foreign key (group_abbreviation_display_id) 
       references name_display (id);

    alter table part_list_item 
       add constraint FK2gikyljpn4k8s5vkw8daln0ds 
       foreign key (group_barline_id) 
       references group_barline (id);

    alter table part_list_item 
       add constraint FKoc0ccefkc660lufjubq22i74u 
       foreign key (group_name_id) 
       references group_name (id);

    alter table part_list_item 
       add constraint FKndqhx5uf7ioraxe58f40123te 
       foreign key (group_name_display_id) 
       references name_display (id);

    alter table part_list_item 
       add constraint FKge6lxiwci7nkwb0rjepf4rv44 
       foreign key (group_symbol_id) 
       references group_symbol (id);

    alter table part_list_item 
       add constraint FKpkla7phyf2f6uolwei3w6jbsk 
       foreign key (identification_id) 
       references identification (id);

    alter table part_list_item 
       add constraint FKafc8pk6d4ug5blkpxxnwkrs0m 
       foreign key (part_abbreviation_id) 
       references part_name (id);

    alter table part_list_item 
       add constraint FK3h2a88nd1y36i0b15mfqe38t4 
       foreign key (part_abbreviation_display_id) 
       references name_display (id);

    alter table part_list_item 
       add constraint FKi6sn8j6i4nkmue6cfs68m5hde 
       foreign key (part_name_id) 
       references part_name (id);

    alter table part_list_item 
       add constraint FKr6r5kkfar6nalujkyuluiqmip 
       foreign key (part_name_display_id) 
       references name_display (id);

    alter table part_list_item 
       add constraint FKhurr7xlscap19a1ub0ggb8rf3 
       foreign key (score_header_id) 
       references score_header (id);

    alter table part_name 
       add constraint FK8w6uv4o5fbjq94cjtmr2r3ey6 
       foreign key (display_id) 
       references display (id);

    alter table part_symbol 
       add constraint FKlbmkyd7lwrme1ij4f1oa7cm6v 
       foreign key (display_id) 
       references display (id);

    alter table pedal_tuning 
       add constraint FKkvm1vni5oi78rbwmadfpcymb8 
       foreign key (harp_pedals_id) 
       references direction_type (id);

    alter table play_type 
       add constraint FKlqyvck4o1sx2npdvpvi2cr0iq 
       foreign key (play_id) 
       references play (id);

    alter table root_alter 
       add constraint FKpe18eow0hfasmtm48jnbb71dm 
       foreign key (display_id) 
       references display (id);

    alter table root_step 
       add constraint FKkpn77a163f8k4cg1ckvfmxdnq 
       foreign key (display_id) 
       references display (id);

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
       add constraint FK7snpsirjcgd5v53th65nrlpcb 
       foreign key (score_part_id) 
       references part_list_item (id);

    alter table score_midi 
       add constraint FKcs9atgsafal12ewyjbh65wdx0 
       foreign key (midi_device_id) 
       references midi_device (id);

    alter table score_midi 
       add constraint FK7nfn04do2a3oyf278dgcorfhi 
       foreign key (midi_instrument_id) 
       references midi_instrument (id);

    alter table score_midi 
       add constraint FKtctr4qp9udoiur2mnjsrixxva 
       foreign key (score_part_id) 
       references part_list_item (id);

    alter table score_part_group 
       add constraint FK1rxas1t6hh8j1iv29kyhii4ep 
       foreign key (score_part_id) 
       references part_list_item (id);

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
       add constraint FKo8qmj3omyxak4f1olkmp2ifba 
       foreign key (display_id) 
       references display (id);

    alter table system_divider 
       add constraint FK6dqjq9npjicmhrnrs1aqyb8eu 
       foreign key (display_id) 
       references display (id);

    alter table system_layout 
       add constraint FKppd150s8ma8ww63sx6ot49uq4 
       foreign key (left_system_divider_id) 
       references system_divider (id);

    alter table system_layout 
       add constraint FKfey58twxxf9bx7y2sabmdtii8 
       foreign key (right_system_divider_id) 
       references system_divider (id);

    alter table technical 
       add constraint FK47clxoexh6shyk117acm8bnan 
       foreign key (display_id) 
       references display (id);

    alter table technical 
       add constraint FKk2qoca6rp76q8da4xyyk8mpug 
       foreign key (bend_sound_id) 
       references bend_sound (id);

    alter table technical 
       add constraint FKj0tk9y5lwir7dkgnr5t1aprvq 
       foreign key (with_bar_id) 
       references bend_with_bar (id);

    alter table technical 
       add constraint FKk1p1j3espd2ej3cfk81elx7qd 
       foreign key (technicals_id) 
       references notation (id);

    alter table text_display 
       add constraint FK35uy6rwu1j5poqmgoo0ft68vl 
       foreign key (display_id) 
       references display (id);

    alter table text_display 
       add constraint FKiv1yjuhytayvijhq3px65i9e6 
       foreign key (text_format_id) 
       references text_format (id);

    alter table text_display 
       add constraint FKshp7l4vh73yjids8ba0xqvn6i 
       foreign key (note_id) 
       references music_data (id);

    alter table text_display 
       add constraint FKewbqxuv4t3j4je18bc13ni6je 
       foreign key (name_display_id) 
       references name_display (id);

    alter table tie 
       add constraint FKj5k6akoif382o12wegpt952g9 
       foreign key (note_id) 
       references music_data (id);

    alter table time 
       add constraint FK41fs0r29g8fwdacunpung5jsg 
       foreign key (display_id) 
       references display (id);

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

    alter table time_signature_type 
       add constraint FKodjdjqe572b06ymjbjnyvvd4w 
       foreign key (interchangeable_id) 
       references interchangeable (id);

    alter table transpose 
       add constraint FKmxnumkuq4nsn7wpf9apuyftda 
       foreign key (attributes_id) 
       references music_data (id);

    alter table tuplet_dot 
       add constraint FKhfuiuu221ljpaisx7x9tmi33 
       foreign key (display_id) 
       references display (id);

    alter table tuplet_dot 
       add constraint FK17shk9waf0d00k9f69xprvof8 
       foreign key (tuplet_portion_id) 
       references tuplet_portion (id);

    alter table tuplet_number 
       add constraint FKh2p1yjera7mawbronu39jqjop 
       foreign key (display_id) 
       references display (id);

    alter table tuplet_portion 
       add constraint FK7vx5g52oawiuajlnkhv6atbyc 
       foreign key (tuplet_number_id) 
       references tuplet_number (id);

    alter table tuplet_portion 
       add constraint FKtwl390gk0ghoye5fu7tnmyef 
       foreign key (tuplet_type_id) 
       references tuplet_type (id);

    alter table tuplet_type 
       add constraint FK9rxhpn2trkuhy5i5s2yp9ogdy 
       foreign key (display_id) 
       references display (id);

    alter table work 
       add constraint FK3tuolhyyg0q9pw6ca6iqyk0at 
       foreign key (opus_id) 
       references link_attributes (id);

    alter table xml_comment 
       add constraint FK3le0xsxpmaagtjiy1625diafq 
       foreign key (score_id) 
       references score (id);
