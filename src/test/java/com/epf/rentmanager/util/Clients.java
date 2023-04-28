package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Client;

import java.time.LocalDate;
import java.time.Period;

public class Clients {

/**
 * Renvoie true si l’utilisateur passé en paramètre a un age >= 18 ans
 *
 * @param client L'instance d’utilisateur à tester
 * @return Résultat du test (>= 18 ans)
 */
    public static boolean isLegal(Client client) {

        return Period.between(client.getBirth_date(), LocalDate.now()).getYears() >= 18;
    }
}
