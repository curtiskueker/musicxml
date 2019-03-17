create sequence hibernate_sequence start with 1 increment by 1;

    create table accidental (
       id number(10,0) not null,
        accidental_type varchar2(255),
        cautionary char(1),
        editorial char(1),
        level_display_id number(10,0),
        print_style_id number(10,0),
        primary key (id)
    );

    create table accord (
       id number(10,0) not null,
        string number(10,0),
        tuning_id number(10,0),
        scordatura_id number(10,0) not null,
        primary key (id)
    );

    create table appearance (
       id number(10,0) not null,
        primary key (id)
    );

    create table articulation (
       articulation_type varchar2(31) not null,
        id number(10,0) not null,
        breath_mark_value varchar2(255),
        placement varchar2(255),
        type varchar2(255),
        print_style_id number(10,0),
        print_placement_id number(10,0),
        line_id number(10,0),
        placement_text_id number(10,0),
        articulations_id number(10,0) not null,
        primary key (id)
    );

    create table bar_style_color (
       id number(10,0) not null,
        bar_style varchar2(255),
        color varchar2(255),
        primary key (id)
    );

    create table barline_repeat (
       id number(10,0) not null,
        direction varchar2(255),
        times number(10,0),
        winged varchar2(255),
        primary key (id)
    );

    create table barre (
       id number(10,0) not null,
        color varchar2(255),
        type varchar2(255),
        primary key (id)
    );

    create table bass (
       id number(10,0) not null,
        bass_alter_id number(10,0),
        bass_step_id number(10,0),
        primary key (id)
    );

    create table bass_alter (
       id number(10,0) not null,
        location varchar2(255),
        print_object char(1),
        semitones number(12,4),
        print_style_id number(10,0),
        primary key (id)
    );

    create table bass_step (
       id number(10,0) not null,
        step varchar2(255),
        text varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table beam (
       id number(10,0) not null,
        color varchar2(255),
        fan varchar2(255),
        beam_number number(10,0),
        repeater char(1),
        type varchar2(255),
        note_id number(10,0) not null,
        primary key (id)
    );

    create table beat_unit (
       id number(10,0) not null,
        beat_unit varchar2(255),
        beat_unit_dots number(10,0),
        primary key (id)
    );

    create table bend_sound (
       id number(10,0) not null,
        accelerate char(1),
        beats number(12,4),
        first_beat number(12,4),
        last_beat number(12,4),
        primary key (id)
    );

    create table bezier (
       id number(10,0) not null,
        bezier_offset number(12,4),
        bezier_offset2 number(12,4),
        bezier_x number(12,4),
        bezier_x2 number(12,4),
        bezier_y number(12,4),
        bezier_y2 number(12,4),
        primary key (id)
    );

    create table cancel (
       id number(10,0) not null,
        fifths number(10,0),
        location varchar2(255),
        primary key (id)
    );

    create table clef (
       id number(10,0) not null,
        additional char(1),
        after_barline char(1),
        clef_octave_change number(10,0),
        line number(10,0),
        clef_number number(10,0),
        print_object char(1),
        sign varchar2(255),
        symbol_size varchar2(255),
        print_style_id number(10,0),
        attributes_id number(10,0) not null,
        primary key (id)
    );

    create table credit (
       id number(10,0) not null,
        page number(10,0),
        score_header_id number(10,0) not null,
        primary key (id)
    );

    create table credit_display (
       credit_display_type varchar2(31) not null,
        id number(10,0) not null,
        formatted_text_id number(10,0),
        image_id number(10,0),
        credit_id number(10,0) not null,
        primary key (id)
    );

    create table credit_type (
       id number(10,0) not null,
        type varchar2(255),
        credit_id number(10,0) not null,
        primary key (id)
    );

    create table dashed_formatting (
       id number(10,0) not null,
        dash_length number(12,4),
        space_length number(12,4),
        primary key (id)
    );

    create table defaults (
       id number(10,0) not null,
        appearance_id number(10,0),
        layout_id number(10,0),
        music_font_id number(10,0),
        defaults_id number(10,0),
        word_font_id number(10,0),
        primary key (id)
    );

    create table degree (
       id number(10,0) not null,
        print_object char(1),
        degree_alter_id number(10,0),
        degree_type_id number(10,0),
        degree_value_id number(10,0),
        harmony_chord_id number(10,0) not null,
        primary key (id)
    );

    create table degree_alter (
       id number(10,0) not null,
        plus_minus char(1),
        semitones number(12,4),
        print_style_id number(10,0),
        primary key (id)
    );

    create table degree_type (
       id number(10,0) not null,
        text varchar2(255),
        value varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table degree_value (
       id number(10,0) not null,
        symbol varchar2(255),
        text varchar2(255),
        value number(10,0),
        print_style_id number(10,0),
        primary key (id)
    );

    create table direction_offset (
       id number(10,0) not null,
        divisions number(12,4),
        sound char(1),
        primary key (id)
    );

    create table direction_type (
       direction_type_type varchar2(31) not null,
        id number(10,0) not null,
        enclosure varchar2(255),
        direction_type varchar2(255),
        accordion_high char(1),
        accordion_low char(1),
        accordion_middle number(10,0),
        color varchar2(255),
        line_type varchar2(255),
        niente char(1),
        direction_type_number number(10,0),
        spread number(12,4),
        value varchar2(255),
        line char(1),
        sign char(1),
        halign varchar2(255),
        source varchar2(255),
        valign_image varchar2(255),
        beater_value varchar2(255),
        tip varchar2(255),
        justify varchar2(255),
        parentheses char(1),
        metronome_relation varchar2(255),
        principal_voice varchar2(255),
        symbol varchar2(255),
        placement varchar2(255),
        print_object char(1),
        stick_material varchar2(255),
        stick_type varchar2(255),
        end_length number(12,4),
        line_end varchar2(255),
        direction_type_size number(10,0),
        direction_type_list_id number(10,0),
        print_style_align_id number(10,0),
        fomatted_text_id number(10,0),
        dashed_formatting_id number(10,0),
        position_id number(10,0),
        text_decoration_id number(10,0),
        print_style_id number(10,0),
        beat_unit_1_id number(10,0),
        beat_unit_2_id number(10,0),
        per_minute_id number(10,0),
        primary key (id)
    );

    create table direction_type_list (
       id number(10,0) not null,
        direction_id number(10,0),
        primary key (id)
    );

    create table directive (
       id number(10,0) not null,
        lang varchar2(255),
        value varchar2(255),
        print_style_id number(10,0),
        attributes_id number(10,0) not null,
        primary key (id)
    );

    create table distance (
       id number(10,0) not null,
        type varchar2(255),
        value number(12,4),
        appearance_id number(10,0) not null,
        primary key (id)
    );

    create table dot (
       id number(10,0) not null,
        print_placement_id number(10,0),
        note_id number(10,0) not null,
        primary key (id)
    );

    create table dynamics_marking (
       id number(10,0) not null,
        dynamics_type varchar2(255),
        dynamics_id number(10,0) not null,
        primary key (id)
    );

    create table editorial (
       id number(10,0) not null,
        footnote_id number(10,0),
        level_id number(10,0),
        primary key (id)
    );

    create table editorial_level (
       id number(10,0) not null,
        reference char(1),
        value varchar2(255),
        level_display_id number(10,0),
        primary key (id)
    );

    create table editorial_voice (
       id number(10,0) not null,
        voice varchar2(255),
        footnote_id number(10,0),
        level_id number(10,0),
        primary key (id)
    );

    create table editorial_voice_direction (
       id number(10,0) not null,
        voice varchar2(255),
        footnote_id number(10,0),
        level_id number(10,0),
        primary key (id)
    );

    create table element_position (
       id number(10,0) not null,
        element varchar2(255),
        position number(10,0),
        primary key (id)
    );

    create table encoding (
       encoding_type varchar2(31) not null,
        id number(10,0) not null,
        attribute varchar2(255),
        element varchar2(255),
        supports_type char(1),
        value varchar2(255),
        software varchar2(255),
        encoding_date date,
        encoding_description varchar2(255),
        encoder_id number(10,0),
        identification_id number(10,0) not null,
        primary key (id)
    );

    create table ending (
       id number(10,0) not null,
        end_length number(12,4),
        ending_number varchar2(255),
        print_object char(1),
        text_x number(12,4),
        text_y number(12,4),
        ending_type varchar2(255),
        value varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table feature (
       id number(10,0) not null,
        type varchar2(255),
        value varchar2(255),
        grouping_id number(10,0) not null,
        primary key (id)
    );

    create table figure (
       id number(10,0) not null,
        extend_id number(10,0),
        figure_number_id number(10,0),
        prefix_id number(10,0),
        suffix_id number(10,0),
        figured_bass_id number(10,0) not null,
        primary key (id)
    );

    create table first_fret (
       id number(10,0) not null,
        location varchar2(255),
        text varchar2(255),
        value number(10,0),
        primary key (id)
    );

    create table font (
       id number(10,0) not null,
        font_family varchar2(255),
        font_style varchar2(255),
        font_weight varchar2(255),
        font_size_id number(10,0),
        primary key (id)
    );

    create table font_size (
       id number(10,0) not null,
        css_font_size varchar2(255),
        font_size number(12,4),
        primary key (id)
    );

    create table formatted_text (
       id number(10,0) not null,
        value clob,
        text_formatting_id number(10,0),
        primary key (id)
    );

    create table frame (
       id number(10,0) not null,
        color varchar2(255),
        frame_frets number(10,0),
        frame_strings number(10,0),
        halign varchar2(255),
        height number(12,4),
        unplayed varchar2(255),
        valign_image varchar2(255),
        width number(12,4),
        first_fret_id number(10,0),
        position_id number(10,0),
        primary key (id)
    );

    create table frame_note (
       id number(10,0) not null,
        barre_id number(10,0),
        fingering_id number(10,0),
        fret_id number(10,0),
        string_id number(10,0),
        frame_id number(10,0) not null,
        primary key (id)
    );

    create table full_note (
       id number(10,0) not null,
        chord char(1),
        full_note_type_id number(10,0),
        primary key (id)
    );

    create table full_note_type (
       full_note_type_name varchar2(31) not null,
        id number(10,0) not null,
        pitch_alter number(12,4),
        octave number(10,0),
        step varchar2(255),
        display_octave number(10,0),
        display_step varchar2(255),
        measure char(1),
        primary key (id)
    );

    create table grace (
       id number(10,0) not null,
        make_time number(12,4),
        slash char(1),
        steal_time_following number(12,4),
        steal_time_previous number(12,4),
        primary key (id)
    );

    create table group_barline (
       id number(10,0) not null,
        color varchar2(255),
        group_barline_type varchar2(255),
        primary key (id)
    );

    create table group_name (
       id number(10,0) not null,
        group_name varchar2(255),
        justify varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table group_symbol (
       id number(10,0) not null,
        color varchar2(255),
        group_symbol_type varchar2(255),
        position_id number(10,0),
        primary key (id)
    );

    create table harmony_chord (
       harmony_chord_type varchar2(31) not null,
        id number(10,0) not null,
        bass_id number(10,0),
        inversion_id number(10,0),
        kind_id number(10,0),
        root_alter_id number(10,0),
        root_step_id number(10,0),
        function_id number(10,0),
        harmony_id number(10,0) not null,
        primary key (id)
    );

    create table identification (
       id number(10,0) not null,
        source varchar2(255),
        miscellaneous_id number(10,0),
        primary key (id)
    );

    create table instrument_type (
       instrument_type varchar2(31) not null,
        id number(10,0) not null,
        value number(10,0),
        primary key (id)
    );

    create table interchangeable (
       id number(10,0) not null,
        time_separator varchar2(255),
        symbol varchar2(255),
        time_relation varchar2(255),
        time_signature_id number(10,0),
        primary key (id)
    );

    create table inversion (
       id number(10,0) not null,
        value number(10,0),
        print_style_id number(10,0),
        primary key (id)
    );

    create table key_octave (
       id number(10,0) not null,
        cancel char(1),
        key_octave_number number(10,0),
        octave number(10,0),
        key_id number(10,0) not null,
        primary key (id)
    );

    create table key_signature (
       key_type varchar2(31) not null,
        id number(10,0) not null,
        key_number number(10,0),
        print_object char(1),
        fifths number(10,0),
        key_mode varchar2(255),
        print_style_id number(10,0),
        cancel_id number(10,0),
        attributes_id number(10,0) not null,
        primary key (id)
    );

    create table kind (
       id number(10,0) not null,
        bracket_degrees char(1),
        halign varchar2(255),
        kind_value varchar2(255),
        parentheses_degrees char(1),
        stack_degrees char(1),
        text varchar2(255),
        use_symbols char(1),
        valign varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table layout (
       id number(10,0) not null,
        page_layout_id number(10,0),
        systemm_layout_id number(10,0),
        primary key (id)
    );

    create table level_display (
       id number(10,0) not null,
        bracket char(1),
        parentheses char(1),
        symbol_size varchar2(255),
        primary key (id)
    );

    create table line (
       id number(10,0) not null,
        line_shape varchar2(255),
        line_type varchar2(255),
        placement varchar2(255),
        dashed_formatting_id number(10,0),
        print_style_id number(10,0),
        primary key (id)
    );

    create table line_width (
       id number(10,0) not null,
        line_width_type varchar2(255),
        value number(12,4),
        appearance_id number(10,0) not null,
        primary key (id)
    );

    create table link_attributes (
       id number(10,0) not null,
        actuate varchar2(255),
        href varchar2(255),
        link_role varchar2(255),
        link_show varchar2(255),
        title varchar2(255),
        type varchar2(255),
        primary key (id)
    );

    create table lyric (
       id number(10,0) not null,
        color varchar2(255),
        end_line char(1),
        end_paragraph char(1),
        justify varchar2(255),
        name varchar2(255),
        lyric_number varchar2(255),
        placement varchar2(255),
        print_object char(1),
        editorial_id number(10,0),
        lyric_item_id number(10,0),
        position_id number(10,0),
        note_id number(10,0) not null,
        primary key (id)
    );

    create table lyric_font (
       id number(10,0) not null,
        name varchar2(255),
        lyric_font_number varchar2(255),
        font_id number(10,0),
        defaults_id number(10,0) not null,
        primary key (id)
    );

    create table lyric_item (
       lyric_item_type varchar2(31) not null,
        id number(10,0) not null,
        type varchar2(255),
        print_style_id number(10,0),
        extend_id number(10,0),
        primary key (id)
    );

    create table lyric_language (
       id number(10,0) not null,
        lang varchar2(255),
        name varchar2(255),
        lyric_language_number varchar2(255),
        defaults_id number(10,0) not null,
        primary key (id)
    );

    create table lyric_syllable (
       id number(10,0) not null,
        syllabic varchar2(255),
        lyric_elision_id number(10,0),
        text_data_id number(10,0),
        lyric_text_id number(10,0) not null,
        primary key (id)
    );

    create table margins (
       id number(10,0) not null,
        bottom_margin number(12,4),
        left_margin number(12,4),
        right_margin number(12,4),
        top_margin number(12,4),
        primary key (id)
    );

    create table measure (
       id number(10,0) not null,
        ordering number(10,0),
        implicit char(1),
        non_controlling char(1),
        measure_number varchar2(255),
        width number(12,4),
        part_id number(10,0) not null,
        primary key (id)
    );

    create table measure_layout (
       id number(10,0) not null,
        measure_distance number(12,4),
        primary key (id)
    );

    create table measure_style (
       measure_style_type varchar2(31) not null,
        id number(10,0) not null,
        color varchar2(255),
        measure_style_number number(10,0),
        use_symbols char(1),
        value number(10,0),
        type varchar2(255),
        use_dots char(1),
        use_stems char(1),
        slashes number(10,0),
        font_id number(10,0),
        slash_group_id number(10,0),
        attributes_id number(10,0) not null,
        primary key (id)
    );

    create table metronome_beam (
       id number(10,0) not null,
        beam_type varchar2(255),
        metronome_beam_number number(10,0),
        metronome_note_id number(10,0) not null,
        primary key (id)
    );

    create table metronome_note (
       id number(10,0) not null,
        metronome_dots number(10,0),
        metronome_type varchar2(255),
        metronome_tuplet_id number(10,0),
        note_metronome_2_id number(10,0) not null,
        note_metronome_1_id number(10,0) not null,
        primary key (id)
    );

    create table metronome_tuplet (
       id number(10,0) not null,
        bracket char(1),
        show_number varchar2(255),
        metronome_tuplet_type varchar2(255),
        time_modification_id number(10,0),
        primary key (id)
    );

    create table midi_device (
       id number(10,0) not null,
        midi_device_id varchar2(255),
        port number(10,0),
        value varchar2(255),
        primary key (id)
    );

    create table midi_instrument (
       id number(10,0) not null,
        elevation number(12,4),
        midi_bank number(10,0),
        midi_channel number(10,0),
        midi_instrumentj_id varchar2(255),
        midi_name varchar2(255),
        midi_program number(10,0),
        midi_unpitched number(10,0),
        pan number(12,4),
        volume number(12,4),
        primary key (id)
    );

    create table miscellaneous (
       id number(10,0) not null,
        primary key (id)
    );

    create table miscellaneous_field (
       id number(10,0) not null,
        name varchar2(255),
        value varchar2(255),
        miscellaneous_id number(10,0) not null,
        primary key (id)
    );

    create table music_data (
       music_data_type varchar2(31) not null,
        id number(10,0) not null,
        ordering number(10,0),
        duration number(12,4),
        coda varchar2(255),
        dacapo char(1),
        dalsegno varchar2(255),
        damper_pedal varchar2(255),
        divisions number(12,4),
        dynamics number(12,4),
        elevation number(12,4),
        fine varchar2(255),
        forward_repeat char(1),
        pan number(12,4),
        pizzicato char(1),
        segno varchar2(255),
        soft_pedal varchar2(255),
        sostenuto_pedal varchar2(255),
        tempo number(12,4),
        time_only varchar2(255),
        tocoda varchar2(255),
        parentheses char(1),
        instruments number(10,0),
        staves number(10,0),
        placement varchar2(255),
        print_frame char(1),
        print_object char(1),
        staff number(10,0),
        type varchar2(255),
        location varchar2(255),
        blank_page number(10,0),
        measure_numbering_value varchar2(255),
        new_page char(1),
        new_system char(1),
        page_number varchar2(255),
        staff_spacing number(12,4),
        grouping_number varchar2(255),
        number_of varchar2(255),
        bookmark_id varchar2(255),
        name varchar2(255),
        attack_length number(12,4),
        color varchar2(255),
        cue char(1),
        end_dynamics number(12,4),
        instrument varchar2(255),
        release_length number(12,4),
        directive char(1),
        measure_id number(10,0),
        editorial_id number(10,0),
        offset_id number(10,0),
        print_style_id number(10,0),
        printout_id number(10,0),
        part_symbol_id number(10,0),
        frame_id number(10,0),
        bar_style_id number(10,0),
        coda_print_id number(10,0),
        ending_id number(10,0),
        repeat_id number(10,0),
        segno_print_id number(10,0),
        wavy_line_id number(10,0),
        layout_id number(10,0),
        measure_layout_id number(10,0),
        part_abbreviation_display_id number(10,0),
        part_name_display_id number(10,0),
        print_style_align_id number(10,0),
        credit_display_id number(10,0),
        element_position_id number(10,0),
        editorial_voice_id number(10,0),
        link_attributes_id number(10,0),
        position_id number(10,0),
        accidental_id number(10,0),
        font_id number(10,0),
        full_note_id number(10,0),
        grace_id number(10,0),
        notehead_id number(10,0),
        notehead_text_id number(10,0),
        play_id number(10,0),
        stem_id number(10,0),
        time_modification_id number(10,0),
        type_id number(10,0),
        editorial_voice_direction_id number(10,0),
        sound_id number(10,0),
        primary key (id)
    );

    create table name_display (
       id number(10,0) not null,
        print_object char(1),
        primary key (id)
    );

    create table non_traditional_key_type (
       id number(10,0) not null,
        key_accidental varchar2(255),
        key_alter number(12,4),
        key_step varchar2(255),
        non_traditional_key_id number(10,0) not null,
        primary key (id)
    );

    create table notation (
       notation_type varchar2(31) not null,
        id number(10,0) not null,
        color varchar2(255),
        notation_number number(10,0),
        placement varchar2(255),
        type_value varchar2(255),
        print_object char(1),
        value varchar2(255),
        line_type varchar2(255),
        connection_type varchar2(255),
        orientation varchar2(255),
        fermata_shape varchar2(255),
        direction varchar2(255),
        bracket char(1),
        line_shape varchar2(255),
        show_number varchar2(255),
        show_type varchar2(255),
        accidental_type varchar2(255),
        notations_id number(10,0),
        position_id number(10,0),
        print_style_id number(10,0),
        dashed_formatting_id number(10,0),
        bezier_id number(10,0),
        bend_sound_id number(10,0),
        barline_id number(10,0),
        tuplet_actual_id number(10,0),
        tuplet_normal_id number(10,0),
        dynamics_id number(10,0),
        primary key (id)
    );

    create table notations (
       id number(10,0) not null,
        print_object char(1),
        editorial_id number(10,0),
        note_id number(10,0) not null,
        primary key (id)
    );

    create table note_size (
       id number(10,0) not null,
        type varchar2(255),
        value number(12,4),
        appearance_id number(10,0) not null,
        primary key (id)
    );

    create table note_type (
       id number(10,0) not null,
        symbol_size varchar2(255),
        note_type_size varchar2(255),
        primary key (id)
    );

    create table notehead (
       id number(10,0) not null,
        color varchar2(255),
        filled char(1),
        parentheses char(1),
        type varchar2(255),
        font_id number(10,0),
        primary key (id)
    );

    create table notehead_text (
       id number(10,0) not null,
        primary key (id)
    );

    create table ornament (
       ornament_type varchar2(31) not null,
        id number(10,0) not null,
        placement varchar2(255),
        slash char(1),
        color varchar2(255),
        wavy_line_number number(10,0),
        connection_type varchar2(255),
        tremolo_marks number(10,0),
        approach varchar2(255),
        departure varchar2(255),
        long_mordent char(1),
        ornaments_id number(10,0),
        placement_text_id number(10,0),
        print_style_id number(10,0),
        trill_sound_id number(10,0),
        position_id number(10,0),
        print_placement_id number(10,0),
        primary key (id)
    );

    create table ornament_accidental (
       id number(10,0) not null,
        accidental_mark_id number(10,0),
        ornaments_id number(10,0) not null,
        primary key (id)
    );

    create table other_appearance (
       id number(10,0) not null,
        type varchar2(255),
        value varchar2(255),
        appearance_id number(10,0) not null,
        primary key (id)
    );

    create table page_layout (
       id number(10,0) not null,
        page_height number(12,4),
        page_width number(12,4),
        primary key (id)
    );

    create table page_margins (
       id number(10,0) not null,
        margin_type_key varchar2(255),
        type varchar2(255),
        margins_id number(10,0),
        page_layout_id number(10,0) not null,
        primary key (id)
    );

    create table part (
       id number(10,0) not null,
        ordering number(10,0),
        part_id varchar2(255),
        score_id number(10,0) not null,
        primary key (id)
    );

    create table part_item (
       part_item_type varchar2(31) not null,
        id number(10,0) not null,
        ordering number(10,0),
        group_time char(1),
        part_group_number varchar2(255),
        part_group_type varchar2(255),
        score_part_id varchar2(255),
        editorial_id number(10,0),
        group_abbreviation_id number(10,0),
        group_abbreviation_display_id number(10,0),
        group_barline_id number(10,0),
        group_name_id number(10,0),
        group_name_display_id number(10,0),
        group_symbol_id number(10,0),
        identification_id number(10,0),
        part_abbreviation_id number(10,0),
        part_abbreviation_display_id number(10,0),
        part_name_id number(10,0),
        part_name_display_id number(10,0),
        part_list_id number(10,0) not null,
        primary key (id)
    );

    create table part_list (
       id number(10,0) not null,
        primary key (id)
    );

    create table part_name (
       id number(10,0) not null,
        part_name varchar2(255),
        part_name_justify varchar2(255),
        part_name_print_object char(1),
        part_name_print_style_id number(10,0),
        primary key (id)
    );

    create table part_symbol (
       id number(10,0) not null,
        bottom_staff number(10,0),
        color varchar2(255),
        group_symbol_type varchar2(255),
        top_staff number(10,0),
        position_id number(10,0),
        primary key (id)
    );

    create table pedal_tuning (
       id number(10,0) not null,
        pedal_alter number(12,4),
        pedal_step varchar2(255),
        harp_pedals_id number(10,0) not null,
        primary key (id)
    );

    create table per_minute (
       id number(10,0) not null,
        per_minute varchar2(255),
        font_id number(10,0),
        primary key (id)
    );

    create table placement_text (
       id number(10,0) not null,
        placement varchar2(255),
        value varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table play (
       id number(10,0) not null,
        play_id varchar2(255),
        primary key (id)
    );

    create table play_type (
       play_type_type varchar2(31) not null,
        id number(10,0) not null,
        value varchar2(255),
        type varchar2(255),
        play_id number(10,0),
        primary key (id)
    );

    create table position (
       id number(10,0) not null,
        default_x number(12,4),
        default_y number(12,4),
        relative_x number(12,4),
        relative_y number(12,4),
        primary key (id)
    );

    create table print_object_style_align (
       id number(10,0) not null,
        print_object char(1),
        print_style_align_id number(10,0),
        primary key (id)
    );

    create table print_placement (
       id number(10,0) not null,
        placement varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table print_style (
       id number(10,0) not null,
        color varchar2(255),
        font_id number(10,0),
        position_id number(10,0),
        primary key (id)
    );

    create table print_style_align (
       id number(10,0) not null,
        halign varchar2(255),
        valign varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table printout (
       id number(10,0) not null,
        print_dot char(1),
        print_lyric char(1),
        print_object char(1),
        print_spacing char(1),
        primary key (id)
    );

    create table root_alter (
       id number(10,0) not null,
        location varchar2(255),
        print_object char(1),
        semitones number(12,4),
        print_style_id number(10,0),
        primary key (id)
    );

    create table root_step (
       id number(10,0) not null,
        step varchar2(255),
        text varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table scaling (
       id number(10,0) not null,
        millimeters number(12,4),
        tenths number(12,4),
        primary key (id)
    );

    create table score (
       id number(10,0) not null,
        score_name varchar2(255),
        version varchar2(255),
        score_header_id number(10,0),
        primary key (id)
    );

    create table score_header (
       id number(10,0) not null,
        movement_number varchar2(255),
        movement_title varchar2(255),
        defaults_id number(10,0),
        identification_id number(10,0),
        part_list_id number(10,0),
        work_id number(10,0),
        primary key (id)
    );

    create table score_instrument (
       id number(10,0) not null,
        instrument_abbreviation varchar2(255),
        instrument_name varchar2(255),
        instrument_sound varchar2(255),
        score_instrument_id varchar2(255),
        instrument_type_id number(10,0),
        virtual_instrument_id number(10,0),
        score_part_id number(10,0) not null,
        primary key (id)
    );

    create table score_midi (
       id number(10,0) not null,
        midi_device_id number(10,0),
        midi_instrument_id number(10,0),
        score_part_id number(10,0) not null,
        primary key (id)
    );

    create table score_part_group (
       id number(10,0) not null,
        group_name varchar2(255),
        score_part_id number(10,0) not null,
        primary key (id)
    );

    create table slash_group (
       id number(10,0) not null,
        slash_dots number(10,0),
        slash_type varchar2(255),
        primary key (id)
    );

    create table sound_midi (
       id number(10,0) not null,
        midi_device_id number(10,0),
        midi_instrument_id number(10,0),
        play_id number(10,0),
        sound_id number(10,0) not null,
        primary key (id)
    );

    create table staff_details (
       id number(10,0) not null,
        capo number(10,0),
        staff_details_number number(10,0),
        print_object char(1),
        print_spacing char(1),
        show_frets varchar2(255),
        staff_lines number(10,0),
        staff_size number(12,4),
        staff_type varchar2(255),
        attributes_id number(10,0) not null,
        primary key (id)
    );

    create table staff_layout (
       id number(10,0) not null,
        staff_layout_number number(10,0),
        staff_distance number(12,4),
        layout_id number(10,0) not null,
        primary key (id)
    );

    create table staff_tuning (
       id number(10,0) not null,
        line number(10,0),
        tuning_id number(10,0),
        staff_details_id number(10,0) not null,
        primary key (id)
    );

    create table stem (
       id number(10,0) not null,
        color varchar2(255),
        type varchar2(255),
        position_id number(10,0),
        primary key (id)
    );

    create table style_text (
       id number(10,0) not null,
        value varchar2(255),
        print_style_id number(10,0),
        primary key (id)
    );

    create table system_dividers (
       id number(10,0) not null,
        left_divider_id number(10,0),
        right_divider_id number(10,0),
        primary key (id)
    );

    create table system_layout (
       id number(10,0) not null,
        left_margin number(12,4),
        right_margin number(12,4),
        system_distance number(12,4),
        top_system_distance number(12,4),
        system_dividers_id number(10,0),
        primary key (id)
    );

    create table technical (
       technical_type varchar2(31) not null,
        id number(10,0) not null,
        placement varchar2(255),
        string_number number(10,0),
        alternate char(1),
        substitution char(1),
        value varchar2(255),
        notation_number number(10,0),
        notation_type varchar2(255),
        color varchar2(255),
        arrow_direction varchar2(255),
        arrow_style varchar2(255),
        circular_arrow varchar2(255),
        hole_closed_location varchar2(255),
        hole_close_type varchar2(255),
        hole_shape varchar2(255),
        hole_type varchar2(255),
        bend_alter number(12,4),
        bend_type varchar2(255),
        handbell_type varchar2(255),
        harmonic_pitch varchar2(255),
        harmonic_type varchar2(255),
        print_object char(1),
        technicals_id number(10,0),
        print_style_id number(10,0),
        print_placement_id number(10,0),
        font_id number(10,0),
        placement_text_id number(10,0),
        bend_sound_id number(10,0),
        with_bar_id number(10,0),
        primary key (id)
    );

    create table text_data (
       id number(10,0) not null,
        color varchar2(255),
        lang varchar2(255),
        letter_spacing varchar2(255),
        text_direction varchar2(255),
        text_rotation number(12,4),
        value varchar2(255),
        font_id number(10,0),
        text_decoration_id number(10,0),
        primary key (id)
    );

    create table text_decoration (
       id number(10,0) not null,
        line_through number(10,0),
        overline number(10,0),
        underline number(10,0),
        primary key (id)
    );

    create table text_display (
       text_type varchar2(31) not null,
        id number(10,0) not null,
        accidental_type varchar2(255),
        name_display_id number(10,0),
        notehead_text_id number(10,0),
        text_formatting_id number(10,0),
        display_text_id number(10,0),
        primary key (id)
    );

    create table text_font_color (
       id number(10,0) not null,
        color varchar2(255),
        lang varchar2(255),
        letter_spacing varchar2(255),
        text_direction varchar2(255),
        text_rotation number(12,4),
        value varchar2(255),
        font_id number(10,0),
        text_decoration_id number(10,0),
        primary key (id)
    );

    create table text_formatting (
       id number(10,0) not null,
        enclosure varchar2(255),
        justify varchar2(255),
        lang varchar2(255),
        letter_spacing varchar2(255),
        line_height varchar2(255),
        space varchar2(255),
        text_direction varchar2(255),
        text_rotation number(12,4),
        print_style_align_id number(10,0),
        text_decoration_id number(10,0),
        primary key (id)
    );

    create table tie (
       id number(10,0) not null,
        time_only varchar2(255),
        type varchar2(255),
        note_id number(10,0) not null,
        primary key (id)
    );

    create table time (
       time_type varchar2(31) not null,
        id number(10,0) not null,
        time_number number(10,0),
        print_object char(1),
        time_separator varchar2(255),
        symbol varchar2(255),
        value varchar2(255),
        print_style_align_id number(10,0),
        interchangeable_id number(10,0),
        attributes_id number(10,0) not null,
        primary key (id)
    );

    create table time_modification (
       id number(10,0) not null,
        actual_notes number(10,0),
        normal_dots number(10,0),
        normal_notes number(10,0),
        normal_type varchar2(255),
        primary key (id)
    );

    create table time_signature_type (
       id number(10,0) not null,
        beat_type varchar2(255),
        beats varchar2(255),
        time_signature_id number(10,0) not null,
        primary key (id)
    );

    create table transpose (
       id number(10,0) not null,
        chromatic number(12,4),
        diatonic number(10,0),
        doubled char(1),
        transpose_number number(10,0),
        octave_change number(10,0),
        attributes_id number(10,0) not null,
        primary key (id)
    );

    create table trill_sound (
       id number(10,0) not null,
        accelerate char(1),
        beats number(12,4),
        last_beat number(12,4),
        second_beat number(12,4),
        start_note varchar2(255),
        trill_step varchar2(255),
        two_note_turn varchar2(255),
        primary key (id)
    );

    create table tuning (
       id number(10,0) not null,
        tuning_alter number(12,4),
        tuning_octave number(10,0),
        tuning_step varchar2(255),
        primary key (id)
    );

    create table tuplet_dot (
       id number(10,0) not null,
        color varchar2(255),
        font_id number(10,0),
        tuplet_portion_id number(10,0) not null,
        primary key (id)
    );

    create table tuplet_number (
       id number(10,0) not null,
        color varchar2(255),
        value number(10,0),
        font_id number(10,0),
        primary key (id)
    );

    create table tuplet_portion (
       id number(10,0) not null,
        tuplet_number_id number(10,0),
        tuplet_type_id number(10,0),
        primary key (id)
    );

    create table tuplet_type (
       id number(10,0) not null,
        color varchar2(255),
        note_type_value varchar2(255),
        font_id number(10,0),
        primary key (id)
    );

    create table typed_text (
       id number(10,0) not null,
        type varchar2(255),
        value varchar2(255),
        creator_id number(10,0),
        relation_id number(10,0),
        rights_id number(10,0),
        primary key (id)
    );

    create table virtual_instrument (
       id number(10,0) not null,
        virtual_library varchar2(255),
        virtual_name varchar2(255),
        primary key (id)
    );

    create table work (
       id number(10,0) not null,
        work_number varchar2(255),
        work_title varchar2(255),
        opus_id number(10,0),
        primary key (id)
    );

    create table xml_comment (
       id number(10,0) not null,
        data varchar2(255),
        next_sibling varchar2(255),
        parent varchar2(255),
        target varchar2(255),
        score_id number(10,0) not null,
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
       add constraint FKllg0e2dchtefge59cy0l0y0j2 
       foreign key (print_placement_id) 
       references print_placement;

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
       add constraint FKhts3e4hthb42jy6kwav33yqyn 
       foreign key (print_placement_id) 
       references print_placement;

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
       add constraint FK1l4jxj9yu15r99y7n5tmpe483 
       foreign key (level_id) 
       references editorial_level;

    alter table editorial_level 
       add constraint FKjrho9g6v6tm3i5ervhove93oe 
       foreign key (level_display_id) 
       references level_display;

    alter table editorial_voice 
       add constraint FK1ustnq8hd3952fjhxmrwjoix0 
       foreign key (footnote_id) 
       references formatted_text;

    alter table editorial_voice 
       add constraint FKmx001y259a2irc105bkrk5uaw 
       foreign key (level_id) 
       references editorial_level;

    alter table editorial_voice_direction 
       add constraint FKcm3f38qna2foj87kxxm2y0icg 
       foreign key (footnote_id) 
       references formatted_text;

    alter table editorial_voice_direction 
       add constraint FKsfpp3tusg9kpih4vvmci6lc4i 
       foreign key (level_id) 
       references editorial_level;

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
       add constraint FKc6m3dofqqgb86xwql4u3d88j 
       foreign key (print_placement_id) 
       references print_placement;

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

    alter table print_placement 
       add constraint FKbcrmiafklcnq8mnbjpmgrqiom 
       foreign key (print_style_id) 
       references print_style;

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
       add constraint FKhn8ttuf8hqy1b3mvb5ngjl0bd 
       foreign key (print_placement_id) 
       references print_placement;

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
