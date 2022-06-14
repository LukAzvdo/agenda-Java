/*

 */
package Enums;

public enum Especialidades {
/*
    clinica_medica,
    ortopedia,
    ginecologia,
    pediatria,
    outros
*/
    clinica_medica("Clinica Medica"),
    ortopedia("Ortopedia"),
    ginecologia("Ginecologia"),
    pediatria("Pediatria"),
    outros("Outros");

    private String descricao;
    
    private Especialidades (String descricao) {
        this.descricao = descricao;
    }
    //
    @Override
    public String toString() {
        return descricao;
    }
}
