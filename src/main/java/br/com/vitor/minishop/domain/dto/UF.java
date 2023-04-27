package br.com.vitor.minishop.domain.dto;

public enum UF {
    AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;

    public static boolean contains(String text) {
        //UF.values pegar todos os labels como string
        for (UF uf : UF.values()) {
            if (uf.name().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
