package Internals;

public enum MicroInstructionEnum {
    //Mar
    pc_to_mar("PC->MAR","PC->MAR",1*Math.pow(2,26)),
    gpr_ad_to_mar("GPR[AD]->MAR","GPR(AD)->MAR",2*Math.pow(2,26)),//No sale en cableado
    sp_to_mar("SP->MAR","SP->MAR",3*Math.pow(2,26)),
    //OPR
    gpr_to_m("GPR->M","GPR->M",1*Math.pow(2,24)),
    qr_to_m("QR->M","QR->M",2*Math.pow(2,24)),
    gpr_op_to_opr("GPR[OP]->OPR","GPR(OP)->OPR",3*Math.pow(2,24)),
    //PC,SP,SC
    pc_plus_to_pc("PC+1->PC","PC+1->PC",1*Math.pow(2,21)),
    gpr_to_pc("GPR->PC","GPR->PC",2*Math.pow(2,21)),
    sp_plus_to_pc("SP+1->SP","SP+1->SP",3*Math.pow(2,21)),
    sp_minus_to_pc("SP-1->SP","SP-1->SP",4*Math.pow(2,21)),
    load_sc("LOAD_SC","LOAD SC",5*Math.pow(2,21),true),
    sc_minus_to_sc("SC-1->SC","SC-1->SC",6*Math.pow(2,21)),
    //GPR
    m_to_gpr("M->GPR","M->GPR",1*Math.pow(2,12)),
    acc_to_gpr("ACC->GPR","ACC->GPR",2*Math.pow(2,12)),
    pc_to_gpr("PC->GPR","PC->GPR",3*Math.pow(2,12)),
    gpr_plus_to_gpr("GPR+1->GPR","GPR+1->GPR",4*Math.pow(2,12)),
    qr_to_gpr("QR->GPR","QR->GPR",5*Math.pow(2,12)),
    neg_gpr_to_gpr("!GPR->GPR","GPR'->GPR",6*Math.pow(2,12)),
    neg_gpr_plus_to_gpr("!GPR+1->GPR","GPR'+1->GPR",7*Math.pow(2,12)),
    //ALU
    zero_to_acc("0->ACC","0->ACC",1*Math.pow(2,15)),
    neg_acc_to_acc("!ACC->ACC","!ACC->ACC",2*Math.pow(2,15)),
    acc_plus_to_acc("ACC+1->ACC","ACC+1->ACC",3*Math.pow(2,15)),
    neg_acc_plus_to_acc("!ACC+1->ACC","ACC'+1->ACC",4*Math.pow(2,15)),
    gpr_plus_acc_to_acc("ACC+GPR->ACC","GPR+ACC->GPR",5*Math.pow(2,15)),
    rol_f_acc("ROL_F_ACC","ROL_F_ACC",6*Math.pow(2,15)),
    ror_f_acc("ROR_F_ACC","ROR_F_ACC",7*Math.pow(2,15)),
    zero_to_qr("0->QR","0->QR",8*Math.pow(2,15)),
    one_to_ovf("1->OVF","1->OVF",9*Math.pow(2,15)),
    zero_to_ovf("0->OVF","0->OVF",10*Math.pow(2,15)),
    zero_to_qn_plus("0->Qn+1","0->Qn+1",11*Math.pow(2,15)),
    neg_qr_plus_to_qr("!QR+1->QR","QR'+1->QR",12*Math.pow(2,15)),
    gpr_to_qr("GPR->QR","GPR->QR",13*Math.pow(2,15)),
    m_to_qr("M->QR","M->QR",14*Math.pow(2,15)),
    one_to_qn("1->Qn","1->Qn",15*Math.pow(2,15)),
    x_to_qs("X->Qs","X->Qs",16*Math.pow(2,15)),
    ashr_acc_qr("ASHR_ACC_QR","ASHR_ACC_QR",17*Math.pow(2,15)),
    rol_f_acc_qr("ROL_F_ACC_QR","ROL_F_ACC_QR",18*Math.pow(2,15)),
    ror_f_acc_qr("ROR_F_ACC_QR","ROR_F-ACC_QR",19*Math.pow(2,15)),
    shl_f_acc_qr("SHL_F_ACC_QR","SHL_F_A_Q",20*Math.pow(2,15)),
    shr_f_acc_qr("SHR_F_ACC_QR","SHR_F_ACC_QR",21*Math.pow(2,15)),
    zero_to_f("0->F","0->F",22*Math.pow(2,15)),
    neg_f_to_f("!F->F","F'->F",23*Math.pow(2,15)),
    neg_gpr_plus_one_plus_acc_to_acc("!GPR+1+ACC->ACC","GPR'+1+ACC->ACC",24*Math.pow(2,15)),
    neg_accqr_plus_to_accqr("!ACCQR+1->ACCQR","ACCQR'+1->ACCQR",25*Math.pow(2,15)),
    zero_to_n("0->N","0->N",26*Math.pow(2,15)),
    one_to_n("1->N","1->N",27*Math.pow(2,15)),
    neg_a_plus_to_a("!A+1->A","!A+1->A",28*Math.pow(2,15)),
    neg_as_to_as("!As->As","As'->As",29*Math.pow(2,15)),
    zero_to_as("0->As","0->As",30*Math.pow(2,15)),
    as_to_qs("As->Qs","As->Qs",31*Math.pow(2,15)),
    qs_xor_bs_to_as("Qs@Bs->As","Qs@Bs->As",32*Math.pow(2,15)),
    qs_xor_bs_to_qs("Qs@Bs->Qs","Qs@Bs->Qs",33*Math.pow(2,15)),
    neg_q_plus_to_q("Q+1->Q","Q'+1->Q",34*Math.pow(2,15)),
    zero_to_a("0->A","0->A",35*Math.pow(2,15)),
    a_plus_b_to_ea("A+B->EA","A+B->EA",36*Math.pow(2,15)),
    a_plus_neg_b_plus_one_to_ea("A+!B+1->EA","A+!B+1->EA",37*Math.pow(2,15)),
    a_plus_neg_b_plus_one_to_a("A+!B+1->A","A+B'+1->A",38*Math.pow(2,15)),
    e_to_ovf("E->OVF","E->OVF",39*Math.pow(2,15)),
    //cable
    sr_plus_to_sr("SR+1->SR","SR+1->SR",0),
    load_sr("LOAD_SR","LOAD SR",0,true);
    public final String inputName;

    public final String outputName;

    public final double microCode;

    public final boolean needsArgument;


    MicroInstructionEnum(String inputName, String outputName, double microProgramadoCode, boolean needsArgument){
        this.inputName = inputName;
        this.outputName = outputName;
        this.microCode = microProgramadoCode;
        this.needsArgument = needsArgument;
    }

    MicroInstructionEnum(String inputName, String outputName, double microProgramadoCode){
        this.inputName = inputName;
        this.outputName = outputName;
        this.microCode = microProgramadoCode;
        this.needsArgument = false;
    }

    public static MicroInstructionEnum valueOfInput(String input){
        for(MicroInstructionEnum mi : values()){
            if(mi.inputName.equals(input)){
                return mi;
            }
        }
        return null;
    }

    public static MicroInstructionEnum valueOfOutput(String output){
        for(MicroInstructionEnum mi : values()){
            if(mi.outputName.equals(output)){
                return mi;
            }
        }
        return null;
    }
    public MicroInstructionTypeEnum getType(){
        if( ordinal()<= 2){
             return MicroInstructionTypeEnum.mar;
        } else if (ordinal() <= 5) {
             return MicroInstructionTypeEnum.opr;
        } else if (ordinal() <= 11) {
            return MicroInstructionTypeEnum.pc_sp_sc;
        } else if (ordinal() <= 18) {
            return MicroInstructionTypeEnum.gpr;
        } else if (ordinal() <= 57) {
            return MicroInstructionTypeEnum.alu;
        }
        else{
            return MicroInstructionTypeEnum.cable;
        }
    }

    public static void main(String[] arg){
        for(MicroInstructionEnum i:values()) {

            System.out.println("- " + i.inputName);
        }


    }
}
