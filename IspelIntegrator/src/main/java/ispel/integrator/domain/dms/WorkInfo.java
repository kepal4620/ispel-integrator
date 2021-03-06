package ispel.integrator.domain.dms;

import java.math.BigDecimal;

public class WorkInfo {

    private String orderGroup;
    private String orderNumber;

    private String pracpoz;
    private String popis_pp;

    private BigDecimal nh;
    private BigDecimal opakovani;
    private BigDecimal cena;
    private BigDecimal cenabdph;

    private Long pp_id;
    private Long procento;

    private String druh_pp;

    private String prijmeni;
    private String jmeno;

    private String ostatni;
    private BigDecimal cena_jednotkova;
    private String hlavna_pp;
    private String vlastna_pp;
    private String saga1;


    public String getOrderGroup() {
        return orderGroup;
    }

    public void setOrderGroup(String orderGroup) {
        this.orderGroup = orderGroup;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPracpoz() {
        return pracpoz;
    }

    public void setPracpoz(String pracpoz) {
        this.pracpoz = pracpoz;
    }

    public String getPopis_pp() {
        return popis_pp;
    }

    public void setPopis_pp(String popis_pp) {
        this.popis_pp = popis_pp;
    }

    public BigDecimal getNh() {
        return nh;
    }

    public void setNh(BigDecimal nh) {
        this.nh = nh;
    }

    public BigDecimal getOpakovani() {
        return opakovani;
    }

    public void setOpakovani(BigDecimal opakovani) {
        this.opakovani = opakovani;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public BigDecimal getCenabdph() {
        return cenabdph;
    }

    public void setCenabdph(BigDecimal cenabdph) {
        this.cenabdph = cenabdph;
    }

    public Long getPp_id() {
        return pp_id;
    }

    public void setPp_id(Long pp_id) {
        this.pp_id = pp_id;
    }

    public Long getProcento() {
        return procento;
    }

    public void setProcento(Long procento) {
        this.procento = procento;
    }

    public String getDruh_pp() {
        return druh_pp;
    }

    public void setDruh_pp(String druh_pp) {
        this.druh_pp = druh_pp;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getOstatni() {
        return ostatni;
    }

    public void setOstatni(String ostatni) {
        this.ostatni = ostatni;
    }

    public BigDecimal getCena_jednotkova() {
        return cena_jednotkova;
    }

    public void setCena_jednotkova(BigDecimal cena_jednotkova) {
        this.cena_jednotkova = cena_jednotkova;
    }

    public String getHlavna_pp() {
        return hlavna_pp;
    }

    public void setHlavna_pp(String hlavna_pp) {
        this.hlavna_pp = hlavna_pp;
    }

    public String getVlastna_pp() {
        return vlastna_pp;
    }

    public void setVlastna_pp(String vlastna_pp) {
        this.vlastna_pp = vlastna_pp;
    }

    public boolean isOther() {
        return "A".equals(ostatni) && !"A".equalsIgnoreCase(vlastna_pp);
    }

    public boolean isVlastni() {
        return !"A".equals(ostatni) || ("A".equals(ostatni) && "A".equalsIgnoreCase(vlastna_pp));
    }

    public boolean isHlavna() {
        return "A".equalsIgnoreCase(hlavna_pp);
    }

    public String getSaga1() {
        return saga1;
    }

    public void setSaga1(String saga1) {
        this.saga1 = saga1;
    }
}
