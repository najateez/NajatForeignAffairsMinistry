package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.RequestObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class Section {

    List<Results> results;
}
