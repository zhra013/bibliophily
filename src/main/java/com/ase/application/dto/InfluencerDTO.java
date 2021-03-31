package com.ase.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InfluencerDTO {

    private UserDTO user;

    private long influenced;
}
