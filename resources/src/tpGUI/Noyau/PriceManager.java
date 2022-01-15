package tpGUI.Noyau;

import java.util.HashMap;
import java.util.Map;

public class PriceManager {

    

    private static Map<Double, Map<Double, Double>> seuilsVente = initVente();
    private static Map<Double, Map<Double, Double>> seuilsEchange = seuilsVente;
    private static Map<Double, Map<Double, Double>> seuilsLocation = initLocation();

    static Map<Double, Map<Double, Double>> initVente() {

        Map<Double, Map<Double, Double>> map = new HashMap<>();

        /*Map qui contient les seuils des prix par mètre carré et les facteurs (frais) correspondants*/
        /* Si prix > 15M */
        Map<Double, Double> seuilPrixMetre1 = new HashMap<>();
        seuilPrixMetre1.put(70000.0, 0.02);
        seuilPrixMetre1.put(0.0, 0.01);

        /*Map qui contient les seuils des prix par mètre carré et les facteurs (frais) correspondants*/
        /* Si prix > 5M */
        Map<Double, Double> seuilPrixMetre2 = new HashMap<>();
        seuilPrixMetre2.put(50000.0, 0.025);
        seuilPrixMetre2.put(0.0, 0.02);

        /*Map qui contient les seuils des prix par mètre carré et les facteurs (frais) correspondants*/
        /* Si prix > 0 */
        Map<Double, Double> seuilPrixMetre3 = new HashMap<>();
        seuilPrixMetre3.put(50000.0, 0.035);
        seuilPrixMetre3.put(0.0, 0.03);

        /*Map qui contient les seuils des prix des biens et les seuils des prix par mètre carré correspondants*/
        map.put(15000000.0, seuilPrixMetre1);
        map.put(5000000.0, seuilPrixMetre2);
        map.put(0.0, seuilPrixMetre3);

        return map;
    }

    public static double ajouterFraisVente(double prixinitial, double prixMetreCarre) {

        double prix = prixinitial;
        double seuilprix = 0, seuilprixMetreCarre = 0;

        for (double seuil : seuilsVente.keySet()) {
            if (prixinitial > seuil) {
                seuilprix = seuil;
                break;
            }
        }

        Map<Double, Double> seuilPrixMetreMap = seuilsVente.get(seuilprix);

        for (double seuil : seuilPrixMetreMap.keySet()) {
            if (prixMetreCarre > seuil) {
                seuilprixMetreCarre = seuil;
                break;
            }
        }

        prix = prixinitial + prixinitial * seuilPrixMetreMap.get(seuilprixMetreCarre);

        return prix;
    }

    static Map<Double, Map<Double, Double>> initLocation() {

        Map<Double, Map<Double, Double>> map = new HashMap<>();

        /*Map qui contient les seuils des prix par mètre carré et les facteurs (frais) correspondants*/
        /* Si superficie > 150m² */
        Map<Double, Double> seuilPrixMetre1 = new HashMap<>();
        seuilPrixMetre1.put(50000.0, 0.015);
        seuilPrixMetre1.put(0.0, 0.01);

        /*Map qui contient les seuils des prix par mètre carré et les facteurs (frais) correspondants*/
        /* Si superficie > 60m² */
        Map<Double, Double> seuilPrixMetre2 = new HashMap<>();
        seuilPrixMetre2.put(50000.0, 0.025);
        seuilPrixMetre2.put(0.0, 0.02);

        /*Map qui contient les seuils des prix par mètre carré et les facteurs (frais) correspondants*/
        /* Si superficie > 0 */
        Map<Double, Double> seuilPrixMetre3 = new HashMap<>();
        seuilPrixMetre3.put(50000.0, 0.035);
        seuilPrixMetre3.put(0.0, 0.03);

        /*Map qui contient les seuils des prix des biens et les seuils des prix par mètre carré correspondants*/
        map.put(150.0, seuilPrixMetre1);
        map.put(60.0, seuilPrixMetre2);
        map.put(0.0, seuilPrixMetre3);

        return map;

    }

    public static double ajouterFraisLocation(double prixinitial, double superficie, double prixMetreCarre) {

        double prix = prixinitial;
        double seuilsuperficie = 0, seuilprixMetreCarre = 0;

        for (double seuil : seuilsLocation.keySet()) {
            if (superficie > seuil) {
                seuilsuperficie = seuil;
                break;
            }
        }

        Map<Double, Double> seuilPrixMetreMap = seuilsLocation.get(seuilsuperficie);

        for (double seuil : seuilPrixMetreMap.keySet()) {
            if (prixMetreCarre > seuil) {
                seuilprixMetreCarre = seuil;
                break;
            }
        }

        prix = prixinitial + prixinitial * seuilPrixMetreMap.get(seuilprixMetreCarre);

        return prix;
    }

    public static double ajouterFraisEchange(double prixinitial, double prixMetreCarre) {

        return ajouterFraisVente(prixinitial, prixMetreCarre);

    }
}
