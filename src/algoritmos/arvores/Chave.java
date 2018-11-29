package algoritmos.arvores;

/**
 * Implementacao da Chave
 */
public class Chave {

    private String data;
    private String hora;
    private int idDeputado;
    private String partido;
    private String estado;
    private String deputado;
    private String cnpj;
    private String descricao;
    private String empresa;
    private float gasto;
    private int id;

    /**
     * Contrutor da classe
     */
    public Chave() {
    }

    /**
     * Construtor da classe
     * @param data
     * @param hora
     * @param idDeputado
     * @param partido
     * @param estado
     * @param deputado
     * @param cnpj
     * @param descricao
     * @param empresa
     * @param gasto 
     */
    public Chave(String data, String hora, int idDeputado, String partido, String estado, String deputado, String cnpj, String descricao, String empresa, float gasto) {
        this.data = data;
        this.hora = hora;
        this.idDeputado = idDeputado;
        this.partido = partido;
        this.estado = estado;
        this.deputado = deputado;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.empresa = empresa;
        this.gasto = gasto;
        this.id = -1;
    }

    /**
     * Construtor da Classe
     * @param data
     * @param hora
     * @param idDeputado
     * @param partido
     * @param estado
     * @param deputado
     * @param cnpj
     * @param descricao
     * @param empresa
     * @param gasto
     * @param id 
     */
    public Chave(String data, String hora, int idDeputado, String partido, String estado, String deputado, String cnpj, String descricao, String empresa, float gasto, int id) {
        this.data = data;
        this.hora = hora;
        this.idDeputado = idDeputado;
        this.partido = partido;
        this.estado = estado;
        this.deputado = deputado;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.empresa = empresa;
        this.gasto = gasto;
        this.id = id;
    }

    /**
     * Retorna o gasto
     * @return gasto
     */
    public float getGasto() {
        return gasto;
    }

    /**
     * Altera o gasto
     * @param gasto 
     */
    public void setGasto(float gasto) {
        this.gasto = gasto;
    }

    /**
     * Retorna a hora do gasto
     * @return data
     */
    public String getData() {
        return data;
    }

    /**
     * Altera a data do gasto
     * @param data 
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Retona a hora do Gasto
     * @return hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * Altera a hora do Gasto
     * @param hora 
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Retorna o Id do Deputado
     * @return idDeputado
     */
    public int getIdDeputado() {
        return idDeputado;
    }

    /**
     * Altera o Id do Deputado
     * @param idDeputado 
     */
    public void setIdDeputado(int idDeputado) {
        this.idDeputado = idDeputado;
    }

    /**
     * Retorna o Partido do deputado
     * @return partido
     */
    public String getPartido() {
        return partido;
    }

    /**
     * Altera o partido do Deputado
     * @param partido 
     */
    public void setPartido(String partido) {
        this.partido = partido;
    }

    /**
     * Retorna o estado do deputado
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Altera o estado do Deputado
     * @param estado 
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Retorna o nome do deputado
     * @return deputado
     */
    public String getDeputado() {
        return deputado;
    }

    /**
     * Altera o nome do deputado
     * @param deputado nome do Deputado
     */
    public void setDeputado(String deputado) {
        this.deputado = deputado;
    }

    /**
     * Retorna o cnpj da empresa prestadora do serviço
     * @return cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Altera o cnpj da empresa prestadora do serviço
     * @param cnpj 
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Retorna a descrição do gasto
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Altera a descricao do gasto
     * @param descricao 
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o nome da empresa prestadora do servico
     * @return empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Altera o nome da empresa prestadora do serviço
     * @param empresa 
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Retorna o identificador da chave
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * ALtera o identificador da chave
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

}
