package DiscoveryExamples.gpca_InfusionMgr;

public class INFUSION_MGR_Functional_Discovery {
    static final int INFUSION_MGR_Functional_IN_ACTIVE = 1;
    static final int INFUSION_MGR_Functional_IN_Basal = 1;
    static final int INFUSION_MGR_Functional_IN_IDLE = 1;
    static final int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
    static final int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
    static final int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
    static final int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
    static final int INFUSION_MGR_Functional_IN_NOT_ON = 2;
    static final int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
    static final int INFUSION_MGR_Functional_IN_OFF = 1;
    static final int INFUSION_MGR_Functional_IN_OFF_b = 2;
    static final int INFUSION_MGR_Functional_IN_ON = 2;
    static final int INFUSION_MGR_Functional_IN_ON_b = 3;
    static final int INFUSION_MGR_Functional_IN_PAUSED = 2;
    static final int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
    static final int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
    static final int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
    static final int INFUSION_MGR_Functional_IN_THERAPY = 2;

    static int INFUSION_MGR_Functional_Step_Scaling_Factor(int inputVal) {
        /* Graphical Function 'Step_Scaling_Factor': '<S1>:4016' */
        /* Transition: '<S1>:4013' */
        return inputVal;
    }

    /* Function for Chart: '<Root>/Infusion Manager Sub-System' */
    static void INFUSION_MGR_Functional_writeLog(int logEvent,
                                                 B_INFUSION_MGR_Functional_c_T localB) {
        /* Graphical Function 'writeLog': '<S1>:3724' */
        /* Transition: '<S1>:3726' */
        localB.IM_OUT_Log_Message_ID = (int) logEvent;
    }


    /* Function for Chart: '<Root>/Infusion Manager Sub-System' */
    static void INFUSION_MGR_Functional_resetForNewInfusion
    (B_INFUSION_MGR_Functional_c_T localB, DW_INFUSION_MGR_Functional_f_T
            localDW) {
        /* Graphical Function 'resetForNewInfusion': '<S1>:3956' */
        /* Transition: '<S1>:3958' */
        localDW.sbolus_timer = 0;
        localDW.pbolus_timer = 0;
        localDW.number_pbolus = 0;
        localDW.sbolusInter_timer = 0;
        localB.IM_OUT_Flow_Rate_Commanded = 0;
        localB.IM_OUT_Actual_Infusion_Duration = 0;
        INFUSION_MGR_Functional_writeLog(1, localB);
    }


    /* Function for Chart: '<Root>/Infusion Manager Sub-System' */
    static void INFUSION_MGR_Functional_exit_internal_ACTIVE
    (DW_INFUSION_MGR_Functional_f_T localDW) {
        /* Exit Internal 'ACTIVE': '<S1>:3905' */
        /* Exit Internal 'Arbiter': '<S1>:3913' */
        localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_Arbiter_c = 0;

        /* Exit Internal 'INTERMITTENT': '<S1>:3936' */
        if (localDW.is_INTERMITTENT == INFUSION_MGR_Functional_IN_ON) {
            /* Exit 'ON': '<S1>:3941' */
            localDW.sbolus_timer++;
            localDW.sbolus_req = false;
            localDW.is_INTERMITTENT = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        } else {
            localDW.is_INTERMITTENT = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        }

        localDW.is_active_INTERMITTENT = 0;

        /* Exit Internal 'PATIENT': '<S1>:3927' */
        if (localDW.is_PATIENT == INFUSION_MGR_Functional_IN_ON_b) {
            /* Exit 'ON': '<S1>:3934' */
            localDW.pbolus_timer++;
            localDW.is_PATIENT = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        } else {
            localDW.is_PATIENT = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        }

        localDW.is_active_PATIENT = 0;

        /* Exit Internal 'BASAL': '<S1>:3907' */
        localDW.is_BASAL = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_BASAL = 0;
    }


    /* Function for Chart: '<Root>/Infusion Manager Sub-System' */
    static void INFUSION_MGR_Functional_exit_internal_PAUSED
    (B_INFUSION_MGR_Functional_c_T localB, DW_INFUSION_MGR_Functional_f_T
            localDW) {
        /* Exit Internal 'PAUSED': '<S1>:3876' */
        /* Exit Internal 'Arbiter': '<S1>:3877' */

        if (localDW.is_Arbiter == INFUSION_MGR_Functional_IN_Manual_Paused_KVO) {
            /* Exit 'Manual_Paused_KVO': '<S1>:3892' */
            localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
            localB.IM_OUT_Current_System_Mode = 8;
            localDW.is_Arbiter = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        } else if (localDW.is_Arbiter == INFUSION_MGR_Functional_IN_Paused_KVO) {
            /* Exit 'Paused_KVO': '<S1>:3891' */
            localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
            localB.IM_OUT_Current_System_Mode = 7;
            localDW.is_Arbiter = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        } else if (localDW.is_Arbiter == INFUSION_MGR_Functional_IN_Paused_NoKVO) {
            /* Exit 'Paused_NoKVO': '<S1>:3890' */
            localB.IM_OUT_Flow_Rate_Commanded = 0;
            localB.IM_OUT_Current_System_Mode = 6;
            localDW.is_Arbiter = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        } else
            localDW.is_Arbiter = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;


        localDW.is_active_Arbiter = 0;

        /* Exit Internal 'Manual_Paused': '<S1>:3899' */
        localDW.is_Manual_Paused = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_Manual_Paused = 0;

        /* Exit Internal 'Alarm_Paused': '<S1>:3893' */
        localDW.is_Alarm_Paused = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_Alarm_Paused = 0;
    }


    static void INFUSION_MGR_Functional_TherapyExitOperations
            (B_INFUSION_MGR_Functional_c_T localB) {
        /* Graphical Function 'TherapyExitOperations': '<S1>:3953' */
        /* Transition: '<S1>:3955' */
        localB.IM_OUT_Flow_Rate_Commanded = 0;
        localB.IM_OUT_New_Infusion = false;
    }

    static int INFUSION_MGR_Functional_sbolus_trigger
            (B_INFUSION_MGR_Functional_c_T localB, DW_INFUSION_MGR_Functional_f_T localDW) {
        int sb;

        /* Graphical Function 'sbolus_trigger': '<S1>:3943' */
        /* Transition: '<S1>:3947' */
        sb = 0;

        int scalingFactor = INFUSION_MGR_Functional_Step_Scaling_Factor(localB.Interval_Intermittent_Bolus);
        int sbolusInter_timer_l = localDW.sbolusInter_timer;

        if (!(((int) sbolusInter_timer_l > scalingFactor ||
                ((int) sbolusInter_timer_l < scalingFactor)) ||
                (!((int) sbolusInter_timer_l == scalingFactor)))) {
            /* Transition: '<S1>:3949' */
            sb = 1;
            localDW.sbolusInter_timer = 0;
        } else {
            /* Transition: '<S1>:3948' */
        }

        return sb;
    }


    /* Function for Chart: '<Root>/Infusion Manager Sub-System' */
    static void INFUSION_MGR_Functional_enter_internal_ACTIVE
    (B_INFUSION_MGR_Functional_c_T localB, DW_INFUSION_MGR_Functional_f_T
            localDW) {
        /* Entry Internal 'ACTIVE': '<S1>:3905' */
        localDW.is_active_BASAL = 1;

        /* Entry Internal 'BASAL': '<S1>:3907' */
        /* Transition: '<S1>:3908' */
        localDW.is_BASAL = INFUSION_MGR_Functional_IN_ON;
        localDW.is_active_PATIENT = 1;

        /* Entry Internal 'PATIENT': '<S1>:3927' */
        if (localDW.inPatientBolus) {
            /* Transition: '<S1>:3930' */
            localDW.is_PATIENT = INFUSION_MGR_Functional_IN_LOCKOUT;

            /* Entry 'LOCKOUT': '<S1>:3935' */
            localDW.lock_timer++;
        } else {
            /* Transition: '<S1>:3928' */
            localDW.is_PATIENT = INFUSION_MGR_Functional_IN_OFF_b;

            /* Entry 'OFF': '<S1>:3933' */
            localDW.pbolus_timer = 0;
            localDW.inPatientBolus = false;
        }

        localDW.is_active_INTERMITTENT = 1;

        /* Entry 'INTERMITTENT': '<S1>:3936' */
        localDW.sbolusInter_timer++;

        /* Entry Internal 'INTERMITTENT': '<S1>:3936' */
        /* Transition: '<S1>:3937' */
        localDW.is_INTERMITTENT = INFUSION_MGR_Functional_IN_OFF;

        /* Entry 'OFF': '<S1>:3940' */
        localDW.sbolus_timer = 0;
        localDW.sbolus_req = false;
        localDW.sbolus_req = (INFUSION_MGR_Functional_sbolus_trigger(localB, localDW)
                != 0);
        localDW.is_active_Arbiter_c = 1;

        /* Entry Internal 'Arbiter': '<S1>:3913' */
        /* Transition: '<S1>:3916' */
        if (localDW.is_PATIENT == INFUSION_MGR_Functional_IN_ON_b) {
            /* Transition: '<S1>:3917' */
            localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Patient_Bolus;

            /* Entry 'Patient_Bolus': '<S1>:3924' */
            localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_Patient_Bolus;
            localB.IM_OUT_Current_System_Mode = 4;
        } else if (localDW.is_INTERMITTENT == INFUSION_MGR_Functional_IN_ON) {
            /* Transition: '<S1>:3918' */
            localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Intermittent_Bolus;

            /* Entry 'Intermittent_Bolus': '<S1>:3925' */
            localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_Intermittent_Bolus;
            localB.IM_OUT_Current_System_Mode = 3;
        } else {
            /* Transition: '<S1>:3919' */
            localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Basal;

            /* Entry 'Basal': '<S1>:3926' */
            localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_Basal;
            localB.IM_OUT_Current_System_Mode = 2;
        }
    }


    /* Function for Chart: '<Root>/Infusion Manager Sub-System' */
    static void INFUSION_MGR_Functional_enter_internal_PAUSED
    (B_INFUSION_MGR_Functional_c_T localB, DW_INFUSION_MGR_Functional_f_T
            localDW) {
        /* Entry Internal 'PAUSED': '<S1>:3876' */
        localDW.is_active_Alarm_Paused = 1;

        /* Entry Internal 'Alarm_Paused': '<S1>:3893' */
        if (localB.Highest_Level_Alarm >= 3) {
            /* Transition: '<S1>:3964' */
            localDW.is_Alarm_Paused = INFUSION_MGR_Functional_IN_ON;
        } else {
            /* Transition: '<S1>:3894' */
            localDW.is_Alarm_Paused = INFUSION_MGR_Functional_IN_OFF;
        }

        localDW.is_active_Manual_Paused = 1;

        /* Entry Internal 'Manual_Paused': '<S1>:3899' */
        if (localB.Infusion_Inhibit) {
            /* Transition: '<S1>:3965' */
            localDW.is_Manual_Paused = INFUSION_MGR_Functional_IN_ON;
        } else {
            /* Transition: '<S1>:3900' */
            localDW.is_Manual_Paused = INFUSION_MGR_Functional_IN_OFF;
        }

        localDW.is_active_Arbiter = 1;

        /* Entry Internal 'Arbiter': '<S1>:3877' */
        /* Transition: '<S1>:3881' */
        int is_Alarm_Paused_l = localDW.is_Alarm_Paused;
        int Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
        if ((is_Alarm_Paused_l == INFUSION_MGR_Functional_IN_ON) && (Highest_Level_Alarm_l == 4)) {
            /* Transition: '<S1>:3882' */
            localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Paused_NoKVO;

            /* Entry 'Paused_NoKVO': '<S1>:3890' */
            localB.IM_OUT_Flow_Rate_Commanded = 0;
            localB.IM_OUT_Current_System_Mode = 6;
        } else if ((is_Alarm_Paused_l == INFUSION_MGR_Functional_IN_ON) && (Highest_Level_Alarm_l == 3)) {
            /* Transition: '<S1>:3884' */
            localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Paused_KVO;

            /* Entry 'Paused_KVO': '<S1>:3891' */
            localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
            localB.IM_OUT_Current_System_Mode = 7;
        } else {
            /* Transition: '<S1>:3883' */
            localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Manual_Paused_KVO;

            /* Entry 'Manual_Paused_KVO': '<S1>:3892' */
            localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
            localB.IM_OUT_Current_System_Mode = 8;
        }
    }


    /* Function for Chart: '<Root>/Infusion Manager Sub-System' */
    static void INFUSION_MGR_Functional_resetAllInfusionDetails
    (B_INFUSION_MGR_Functional_c_T localB, DW_INFUSION_MGR_Functional_f_T
            localDW) {
        /* Graphical Function 'resetAllInfusionDetails': '<S1>:3959' */
        /* Transition: '<S1>:3961' */
        INFUSION_MGR_Functional_resetForNewInfusion(localB, localDW);
        localDW.lock_timer = 0;
        localDW.inPatientBolus = false;
        localDW.number_pbolus = 0;
    }

    static void INFUSION_MGR_Functional_THERAPY(B_INFUSION_MGR_Functional_c_T
                                                        localB, DW_INFUSION_MGR_Functional_f_T localDW) {
        //DB_prinTF("1: %2x %2x ",localB.Infusion_Initiate,localB.Reservoir_Empty);

        /* During 'THERAPY': '<S1>:3867' */
        boolean Infusion_Initiate_l = localB.Infusion_Initiate;
        boolean Reservoir_Empty_l = localB.Reservoir_Empty;
        int Configured_l = localB.Configured;
        boolean Infusion_Cancel_l = localB.Infusion_Cancel;

        if ((Infusion_Initiate_l && Reservoir_Empty_l) ||
                (Configured_l < 1) || Infusion_Cancel_l) {
            /* Transition: '<S1>:3987' */
            /* Exit Internal 'THERAPY': '<S1>:3867' */
            if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_ACTIVE) {
                INFUSION_MGR_Functional_exit_internal_ACTIVE(localDW);
                localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
            } else if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_PAUSED) {
                INFUSION_MGR_Functional_exit_internal_PAUSED(localB, localDW);
                localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
            } else
                localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;


            /* Exit 'THERAPY': '<S1>:3867' */
            INFUSION_MGR_Functional_TherapyExitOperations(localB);
            localDW.is_Infusion_Manager = INFUSION_MGR_Functional_IN_IDLE;

            /* Entry 'IDLE': '<S1>:3866' */
            localB.IM_OUT_Current_System_Mode = 1;
            localB.IM_OUT_Flow_Rate_Commanded = 0;
            INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);
        } else if (Infusion_Initiate_l && (Configured_l == 1) &&
                Reservoir_Empty_l) {
            /* Transition: '<S1>:3861' */
            INFUSION_MGR_Functional_resetForNewInfusion(localB, localDW);

            /* Transition: '<S1>:3863' */
            localB.IM_OUT_New_Infusion = true;

            /* Exit Internal 'THERAPY': '<S1>:3867' */
            if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_ACTIVE) {
                INFUSION_MGR_Functional_exit_internal_ACTIVE(localDW);
                localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
            } else if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_PAUSED) {
                INFUSION_MGR_Functional_exit_internal_PAUSED(localB, localDW);
                localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
            } else
                localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;


            /* Exit 'THERAPY': '<S1>:3867' */
            INFUSION_MGR_Functional_TherapyExitOperations(localB);
            localDW.is_Infusion_Manager = INFUSION_MGR_Functional_IN_THERAPY;

            /* Entry Internal 'THERAPY': '<S1>:3867' */
            boolean Infusion_Inhibit_l = localB.Infusion_Inhibit;
            int Highest_Level_Alarm_l = localB.Highest_Level_Alarm;

            if (Infusion_Inhibit_l || (Highest_Level_Alarm_l >= 3)) {
                /* Transition: '<S1>:3994' */
                localDW.is_THERAPY = INFUSION_MGR_Functional_IN_PAUSED;
                INFUSION_MGR_Functional_enter_internal_PAUSED(localB, localDW);
            } else {
                /* Transition: '<S1>:3875' */
                localDW.is_THERAPY = INFUSION_MGR_Functional_IN_ACTIVE;
                INFUSION_MGR_Functional_enter_internal_ACTIVE(localB, localDW);
            }
        } else {
            int IM_OUT_Actual_Infusion_Duration_l = localB.IM_OUT_Actual_Infusion_Duration;
            int Volume_Infused_l = localB.Volume_Infused;
            int VTBI_Total_l = localB.VTBI_Total;
            int scalingFactor = INFUSION_MGR_Functional_Step_Scaling_Factor((int) (localB.Infusion_Total_Duration - 1));
            if (((int) IM_OUT_Actual_Infusion_Duration_l >= scalingFactor) || (Volume_Infused_l >= VTBI_Total_l)) {
                /* Transition: '<S1>:3865' */
                /* Exit Internal 'THERAPY': '<S1>:3867' */

                if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_ACTIVE) {
                    INFUSION_MGR_Functional_exit_internal_ACTIVE(localDW);
                    localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
                } else if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_PAUSED) {
                    INFUSION_MGR_Functional_exit_internal_PAUSED(localB, localDW);
                    localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
                } else
                    localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;


                /* Exit 'THERAPY': '<S1>:3867' */
                INFUSION_MGR_Functional_TherapyExitOperations(localB);
                localDW.is_Infusion_Manager = INFUSION_MGR_Functional_IN_IDLE;

                /* Entry 'IDLE': '<S1>:3866' */
                localB.IM_OUT_Current_System_Mode = 1;
                localB.IM_OUT_Flow_Rate_Commanded = 0;
                INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);
            } else {
                localB.IM_OUT_New_Infusion = false;
                if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_ACTIVE) {
                    /* During 'ACTIVE': '<S1>:3905' */
                    boolean Infusion_Inhibit_l = localB.Infusion_Inhibit;
                    int Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
                    if (Infusion_Inhibit_l || (Highest_Level_Alarm_l >= 3)) {
                        /* Transition: '<S1>:3871' */
                        INFUSION_MGR_Functional_exit_internal_ACTIVE(localDW);
                        localDW.is_THERAPY = INFUSION_MGR_Functional_IN_PAUSED;
                        INFUSION_MGR_Functional_enter_internal_PAUSED(localB, localDW);
                    } else {
                        /* During 'BASAL': '<S1>:3907' */
                        if (localDW.is_BASAL == INFUSION_MGR_Functional_IN_OFF) {
                            /* During 'OFF': '<S1>:3912' */
                            if (localB.Infusion_Initiate) {
                                /* Transition: '<S1>:3909' */
                                localDW.is_BASAL = INFUSION_MGR_Functional_IN_ON;
                            }
                        } else {
                            /* During 'ON': '<S1>:3911' */
                            scalingFactor =
                                    INFUSION_MGR_Functional_Step_Scaling_Factor((int) (localB.Infusion_Total_Duration - 1));
                            if ((int) localB.IM_OUT_Actual_Infusion_Duration >= scalingFactor) {
                                /* Transition: '<S1>:3910' */
                                localDW.is_BASAL = INFUSION_MGR_Functional_IN_OFF;
                            }
                        }

                        /* During 'PATIENT': '<S1>:3927' */

                        if (localDW.is_PATIENT == INFUSION_MGR_Functional_IN_LOCKOUT) {
                            /* During 'LOCKOUT': '<S1>:3935' */
                            scalingFactor = INFUSION_MGR_Functional_Step_Scaling_Factor((int)
                                    (localB.Lockout_Period_Patient_Bolus - 1));
                            if ((int) localDW.lock_timer >= scalingFactor) {
                                /* Transition: '<S1>:3931' */
                                localDW.is_PATIENT = INFUSION_MGR_Functional_IN_OFF_b;
                                /* Entry 'OFF': '<S1>:3933' */
                                localDW.pbolus_timer = 0;
                                localDW.inPatientBolus = false;
                            } else {
                                localDW.lock_timer++;
                            }
                        } else if (localDW.is_PATIENT == INFUSION_MGR_Functional_IN_OFF_b) {
                            /* During 'OFF': '<S1>:3933' */
                            boolean Patient_Bolus_Request_l = localB.Patient_Bolus_Request;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
                            int number_pbolus_l = localDW.number_pbolus;
                            int Max_Number_of_Patient_Bolus_l = localB.Max_Number_of_Patient_Bolus;

                            if (Patient_Bolus_Request_l && (Highest_Level_Alarm_l < 2) && (number_pbolus_l < Max_Number_of_Patient_Bolus_l)) {
                                /* Transition: '<S1>:3929' */
                                localDW.is_PATIENT = INFUSION_MGR_Functional_IN_ON_b;

                                /* Entry 'ON': '<S1>:3934' */
                                localDW.number_pbolus++;
                                localDW.inPatientBolus = true;
                                localDW.pbolus_timer++;
                            }
                        } else {
                            /* During 'ON': '<S1>:3934' */
                            scalingFactor = INFUSION_MGR_Functional_Step_Scaling_Factor((int) (localB.Duration_Patient_Bolus - 1));
                            int pbolus_timer_l = localDW.pbolus_timer;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
                            if (((int) pbolus_timer_l >= scalingFactor) ||
                                    (Highest_Level_Alarm_l == 2)) {
                                /* Transition: '<S1>:3932' */
                                localDW.lock_timer = 0;

                                /* Exit 'ON': '<S1>:3934' */
                                localDW.pbolus_timer++;
                                localDW.is_PATIENT = INFUSION_MGR_Functional_IN_LOCKOUT;

                                /* Entry 'LOCKOUT': '<S1>:3935' */
                                localDW.lock_timer++;
                            } else {
                                localDW.pbolus_timer++;
                            }
                        }


                        /* During 'INTERMITTENT': '<S1>:3936' */
                        localDW.sbolusInter_timer++;
                        if (localDW.is_INTERMITTENT == INFUSION_MGR_Functional_IN_OFF) {
                            /* During 'OFF': '<S1>:3940' */
                            boolean sbolus_req_l = localDW.sbolus_req;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;

                            if (sbolus_req_l && (Highest_Level_Alarm_l < 2)) {
                                /* Transition: '<S1>:3938' */
                                localDW.is_INTERMITTENT = INFUSION_MGR_Functional_IN_ON;
                            } else {
                                localDW.sbolus_req = (INFUSION_MGR_Functional_sbolus_trigger
                                        (localB, localDW) != 0);
                            }
                        } else {
                            /* During 'ON': '<S1>:3941' */
                            scalingFactor = INFUSION_MGR_Functional_Step_Scaling_Factor((int) (localB.Duration_Intermittent_Bolus - 1));
                            int sbolus_timer_l = localDW.sbolus_timer;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
                            if (((int) sbolus_timer_l >= scalingFactor) ||
                                    (Highest_Level_Alarm_l == 2)) {
                                /* Transition: '<S1>:3939' */
                                /* Exit 'ON': '<S1>:3941' */
                                localDW.is_INTERMITTENT = INFUSION_MGR_Functional_IN_OFF;

                                /* Entry 'OFF': '<S1>:3940' */
                                localDW.sbolus_timer = 0;
                                localDW.sbolus_req = false;
                                localDW.sbolus_req = (INFUSION_MGR_Functional_sbolus_trigger
                                        (localB, localDW) != 0);
                            } else {
                                localDW.sbolus_timer++;
                                localDW.sbolus_req = false;
                            }
                        }

                        /* During 'Arbiter': '<S1>:3913' */
                        if (localDW.is_Arbiter_d == INFUSION_MGR_Functional_IN_Basal) {
                            /* During 'Basal': '<S1>:3926' */
                            /* Transition: '<S1>:3923' */
                            /* Transition: '<S1>:3920' */
                            localB.IM_OUT_Actual_Infusion_Duration++;

                            /* Transition: '<S1>:4002' */
                            if (localDW.is_PATIENT == INFUSION_MGR_Functional_IN_ON_b) {
                                /* Transition: '<S1>:3917' */
                                localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Patient_Bolus;

                                /* Entry 'Patient_Bolus': '<S1>:3924' */
                                localB.IM_OUT_Flow_Rate_Commanded =
                                        localB.Flow_Rate_Patient_Bolus;
                                localB.IM_OUT_Current_System_Mode = 4;
                            } else if (localDW.is_INTERMITTENT == INFUSION_MGR_Functional_IN_ON) {
                                /* Transition: '<S1>:3918' */
                                localDW.is_Arbiter_d =
                                        INFUSION_MGR_Functional_IN_Intermittent_Bolus;

                                /* Entry 'Intermittent_Bolus': '<S1>:3925' */
                                localB.IM_OUT_Flow_Rate_Commanded =
                                        localB.Flow_Rate_Intermittent_Bolus;
                                localB.IM_OUT_Current_System_Mode = 3;
                            } else {
                                /* Transition: '<S1>:3919' */
                                localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Basal;

                                /* Entry 'Basal': '<S1>:3926' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_Basal;
                                localB.IM_OUT_Current_System_Mode = 2;
                            }
                        } else if (localDW.is_Arbiter_d == INFUSION_MGR_Functional_IN_Intermittent_Bolus) {
                            /* During 'Intermittent_Bolus': '<S1>:3925' */
                            /* Transition: '<S1>:3922' */
                            /* Transition: '<S1>:3920' */
                            localB.IM_OUT_Actual_Infusion_Duration++;

                            /* Transition: '<S1>:4002' */
                            if (localDW.is_PATIENT == INFUSION_MGR_Functional_IN_ON_b) {
                                /* Transition: '<S1>:3917' */
                                localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Patient_Bolus;

                                /* Entry 'Patient_Bolus': '<S1>:3924' */
                                localB.IM_OUT_Flow_Rate_Commanded =
                                        localB.Flow_Rate_Patient_Bolus;
                                localB.IM_OUT_Current_System_Mode = 4;
                            } else if (localDW.is_INTERMITTENT == INFUSION_MGR_Functional_IN_ON) {
                                /* Transition: '<S1>:3918' */
                                localDW.is_Arbiter_d =
                                        INFUSION_MGR_Functional_IN_Intermittent_Bolus;

                                /* Entry 'Intermittent_Bolus': '<S1>:3925' */
                                localB.IM_OUT_Flow_Rate_Commanded =
                                        localB.Flow_Rate_Intermittent_Bolus;
                                localB.IM_OUT_Current_System_Mode = 3;
                            } else {
                                /* Transition: '<S1>:3919' */
                                localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Basal;

                                /* Entry 'Basal': '<S1>:3926' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_Basal;
                                localB.IM_OUT_Current_System_Mode = 2;
                            }
                        } else {
                            /* During 'Patient_Bolus': '<S1>:3924' */
                            /* Transition: '<S1>:3921' */
                            /* Transition: '<S1>:3920' */
                            localB.IM_OUT_Actual_Infusion_Duration++;

                            /* Transition: '<S1>:4002' */
                            if (localDW.is_PATIENT == INFUSION_MGR_Functional_IN_ON_b) {
                                /* Transition: '<S1>:3917' */
                                localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Patient_Bolus;

                                /* Entry 'Patient_Bolus': '<S1>:3924' */
                                localB.IM_OUT_Flow_Rate_Commanded =
                                        localB.Flow_Rate_Patient_Bolus;
                                localB.IM_OUT_Current_System_Mode = 4;
                            } else if (localDW.is_INTERMITTENT == INFUSION_MGR_Functional_IN_ON) {
                                /* Transition: '<S1>:3918' */
                                localDW.is_Arbiter_d =
                                        INFUSION_MGR_Functional_IN_Intermittent_Bolus;

                                /* Entry 'Intermittent_Bolus': '<S1>:3925' */
                                localB.IM_OUT_Flow_Rate_Commanded =
                                        localB.Flow_Rate_Intermittent_Bolus;
                                localB.IM_OUT_Current_System_Mode = 3;
                            } else {
                                /* Transition: '<S1>:3919' */
                                localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_Basal;

                                /* Entry 'Basal': '<S1>:3926' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_Basal;
                                localB.IM_OUT_Current_System_Mode = 2;
                            }
                        }
                    }
                } else {
                    /* During 'PAUSED': '<S1>:3876' */
                    Infusion_Initiate_l = localB.Infusion_Initiate;
                    int Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
                    boolean Infusion_Inhibit_l = localB.Infusion_Inhibit;
                    if (Infusion_Initiate_l && (Highest_Level_Alarm_l < 3) && (!Infusion_Inhibit_l)) {
                        /* Transition: '<S1>:3870' */
                        INFUSION_MGR_Functional_exit_internal_PAUSED(localB, localDW);
                        localDW.is_THERAPY = INFUSION_MGR_Functional_IN_ACTIVE;
                        INFUSION_MGR_Functional_enter_internal_ACTIVE(localB, localDW);
                    } else {
                        /* During 'Alarm_Paused': '<S1>:3893' */
                        if (localDW.is_Alarm_Paused == INFUSION_MGR_Functional_IN_OFF) {
                            /* During 'OFF': '<S1>:3897' */
                            if (localB.Highest_Level_Alarm >= 3) {
                                /* Transition: '<S1>:3895' */
                                localDW.is_Alarm_Paused = INFUSION_MGR_Functional_IN_ON;
                            }
                        } else {
                            /* During 'ON': '<S1>:3898' */
                            Infusion_Initiate_l = localB.Infusion_Initiate;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
                            if (Infusion_Initiate_l && (Highest_Level_Alarm_l < 3)) {
                                /* Transition: '<S1>:3896' */
                                localDW.is_Alarm_Paused = INFUSION_MGR_Functional_IN_OFF;
                            }
                        }

                        /* During 'Manual_Paused': '<S1>:3899' */
                        if (localDW.is_Manual_Paused == INFUSION_MGR_Functional_IN_OFF) {
                            /* During 'OFF': '<S1>:3903' */
                            if (localB.Infusion_Inhibit) {
                                /* Transition: '<S1>:3901' */
                                localDW.is_Manual_Paused = INFUSION_MGR_Functional_IN_ON;
                            }
                        } else {
                            /* During 'ON': '<S1>:3904' */
                            Infusion_Initiate_l = localB.Infusion_Initiate;
                            Infusion_Inhibit_l = localB.Infusion_Inhibit;
                            if (Infusion_Initiate_l && (!Infusion_Inhibit_l)) {
                                /* Transition: '<S1>:3902' */
                                localDW.is_Manual_Paused = INFUSION_MGR_Functional_IN_OFF;
                            }
                        }

                        /* During 'Arbiter': '<S1>:3877' */
                        if (localDW.is_Arbiter == INFUSION_MGR_Functional_IN_Manual_Paused_KVO) {
                            /* During 'Manual_Paused_KVO': '<S1>:3892' */
                            /* Transition: '<S1>:3888' */
                            /* Transition: '<S1>:3889' */
                            /* Transition: '<S1>:3885' */
                            int is_Alarm_Paused_l = localDW.is_Alarm_Paused;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;
                            if ((is_Alarm_Paused_l == INFUSION_MGR_Functional_IN_ON) && (Highest_Level_Alarm_l == 4)) {
                                /* Transition: '<S1>:3882' */
                                /* Exit 'Manual_Paused_KVO': '<S1>:3892' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Paused_NoKVO;

                                /* Entry 'Paused_NoKVO': '<S1>:3890' */
                                localB.IM_OUT_Flow_Rate_Commanded = 0;
                                localB.IM_OUT_Current_System_Mode = 6;
                            } else if ((is_Alarm_Paused_l == INFUSION_MGR_Functional_IN_ON) && (Highest_Level_Alarm_l == 3)) {
                                /* Transition: '<S1>:3884' */
                                /* Exit 'Manual_Paused_KVO': '<S1>:3892' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Paused_KVO;

                                /* Entry 'Paused_KVO': '<S1>:3891' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
                                localB.IM_OUT_Current_System_Mode = 7;
                            } else {
                                /* Transition: '<S1>:3883' */
                                /* Exit 'Manual_Paused_KVO': '<S1>:3892' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Manual_Paused_KVO;

                                /* Entry 'Manual_Paused_KVO': '<S1>:3892' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
                                localB.IM_OUT_Current_System_Mode = 8;
                            }
                        } else if (localDW.is_Arbiter == INFUSION_MGR_Functional_IN_Paused_KVO) {
                            int is_Alarm_Paused_l = localDW.is_Alarm_Paused;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;

                            /* During 'Paused_KVO': '<S1>:3891' */
                            /* Transition: '<S1>:3887' */
                            /* Transition: '<S1>:3889' */
                            /* Transition: '<S1>:3885' */
                            if ((is_Alarm_Paused_l == INFUSION_MGR_Functional_IN_ON) && (Highest_Level_Alarm_l == 4)) {
                                /* Transition: '<S1>:3882' */
                                /* Exit 'Paused_KVO': '<S1>:3891' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Paused_NoKVO;

                                /* Entry 'Paused_NoKVO': '<S1>:3890' */
                                localB.IM_OUT_Flow_Rate_Commanded = 0;
                                localB.IM_OUT_Current_System_Mode = 6;
                            } else if ((is_Alarm_Paused_l == INFUSION_MGR_Functional_IN_ON) && (Highest_Level_Alarm_l == 3)) {
                                /* Transition: '<S1>:3884' */
                                /* Exit 'Paused_KVO': '<S1>:3891' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Paused_KVO;

                                /* Entry 'Paused_KVO': '<S1>:3891' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
                                localB.IM_OUT_Current_System_Mode = 7;
                            } else {
                                /* Transition: '<S1>:3883' */
                                /* Exit 'Paused_KVO': '<S1>:3891' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Manual_Paused_KVO;

                                /* Entry 'Manual_Paused_KVO': '<S1>:3892' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
                                localB.IM_OUT_Current_System_Mode = 8;
                            }
                        } else {
                            /* During 'Paused_NoKVO': '<S1>:3890' */
                            /* Transition: '<S1>:3886' */
                            /* Transition: '<S1>:3889' */
                            /* Transition: '<S1>:3885' */
                            int is_Alarm_Paused_l = localDW.is_Alarm_Paused;
                            Highest_Level_Alarm_l = localB.Highest_Level_Alarm;

                            if ((is_Alarm_Paused_l == INFUSION_MGR_Functional_IN_ON) && (Highest_Level_Alarm_l == 4)) {
                                /* Transition: '<S1>:3882' */
                                /* Exit 'Paused_NoKVO': '<S1>:3890' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Paused_NoKVO;

                                /* Entry 'Paused_NoKVO': '<S1>:3890' */
                                localB.IM_OUT_Flow_Rate_Commanded = 0;
                                localB.IM_OUT_Current_System_Mode = 6;
                            } else if ((is_Alarm_Paused_l == INFUSION_MGR_Functional_IN_ON) && (Highest_Level_Alarm_l == 3)) {
                                /* Transition: '<S1>:3884' */
                                /* Exit 'Paused_NoKVO': '<S1>:3890' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Paused_KVO;

                                /* Entry 'Paused_KVO': '<S1>:3891' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
                                localB.IM_OUT_Current_System_Mode = 7;
                            } else {
                                /* Transition: '<S1>:3883' */
                                /* Exit 'Paused_NoKVO': '<S1>:3890' */
                                localDW.is_Arbiter = INFUSION_MGR_Functional_IN_Manual_Paused_KVO;

                                /* Entry 'Manual_Paused_KVO': '<S1>:3892' */
                                localB.IM_OUT_Flow_Rate_Commanded = localB.Flow_Rate_KVO;
                                localB.IM_OUT_Current_System_Mode = 8;
                            }
                        }
                    }
                }
            }
        }

    }


    /* Initial conditions for referenced model: 'INFUSION_MGR_Functional' */
    static void INFUSION_MGR_Functional_Init(B_INFUSION_MGR_Functional_c_T localB,
                                             DW_INFUSION_MGR_Functional_f_T localDW) {
        /* InitializeConditions for Chart: '<Root>/Infusion Manager Sub-System' */
      /*  localDW.is_Infusion_Manager = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_Arbiter_c = 0;
        localDW.is_Arbiter_d = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_BASAL = 0;
        localDW.is_BASAL = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_INTERMITTENT = 0;
        localDW.is_INTERMITTENT = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_PATIENT = 0;
        localDW.is_PATIENT = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_Alarm_Paused = 0;
        localDW.is_Alarm_Paused = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_Arbiter = 0;
        localDW.is_Arbiter = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_Manual_Paused = 0;
        localDW.is_Manual_Paused = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.is_active_c2_INFUSION_MGR_Functional = 0;
        localDW.is_c2_INFUSION_MGR_Functional = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
        localDW.sbolus_req = false;
        localDW.sbolus_timer = 0;
        localDW.pbolus_timer = 0;
        localDW.lock_timer = 0;
        localDW.number_pbolus = 0;
        localDW.inPatientBolus = false;
        localDW.sbolusInter_timer = 0;
        localB.IM_OUT_Flow_Rate_Commanded = 0;
        localB.IM_OUT_Current_System_Mode = 0;
        localB.IM_OUT_New_Infusion = false;
        localB.IM_OUT_Log_Message_ID = 0;
        localB.IM_OUT_Actual_Infusion_Duration = 0;*/
    }

    /* Output and update for referenced model: 'INFUSION_MGR_Functional' */
    static void INFUSION_MGR_Functional(Top_Level_Mode_Outputs rtu_TLM_MODE_IN,
                                        Operator_Commands rtu_OP_CMD_IN, Patient_Inputs rtu_PATIENT_IN,
                                        Config_Outputs rtu_CONFIG_IN, Alarm_Outputs rtu_ALARM_IN,
                                        System_Status_Outputs rtu_SYS_STAT_IN, Infusion_Manager_Outputs rty_IM_OUT,
                                        B_INFUSION_MGR_Functional_c_T localB, DW_INFUSION_MGR_Functional_f_T localDW) {
        /* BusSelector: '<Root>/BusConversion_InsertedFor_ALARM_IN_at_outport_0' */
        localB.Highest_Level_Alarm = rtu_ALARM_IN.Highest_Level_Alarm;

        /* BusSelector: '<Root>/BusConversion_InsertedFor_CONFIG_IN_at_outport_0' */
        localB.Infusion_Total_Duration = rtu_CONFIG_IN.Infusion_Total_Duration;
        localB.VTBI_Total = rtu_CONFIG_IN.VTBI_Total;
        localB.Flow_Rate_Basal = rtu_CONFIG_IN.Flow_Rate_Basal;
        localB.Flow_Rate_Intermittent_Bolus =
                rtu_CONFIG_IN.Flow_Rate_Intermittent_Bolus;
        localB.Duration_Intermittent_Bolus =
                rtu_CONFIG_IN.Duration_Intermittent_Bolus;
        localB.Interval_Intermittent_Bolus =
                rtu_CONFIG_IN.Interval_Intermittent_Bolus;
        localB.Flow_Rate_Patient_Bolus = rtu_CONFIG_IN.Flow_Rate_Patient_Bolus;
        localB.Duration_Patient_Bolus = rtu_CONFIG_IN.Duration_Patient_Bolus;
        localB.Lockout_Period_Patient_Bolus =
                rtu_CONFIG_IN.Lockout_Period_Patient_Bolus;
        localB.Max_Number_of_Patient_Bolus =
                rtu_CONFIG_IN.Max_Number_of_Patient_Bolus;
        localB.Flow_Rate_KVO = rtu_CONFIG_IN.Flow_Rate_KVO;
        localB.Configured = rtu_CONFIG_IN.Configured;

        /* BusSelector: '<Root>/BusConversion_InsertedFor_OP_CMD_IN_at_outport_0' */
        localB.Infusion_Initiate = rtu_OP_CMD_IN.Infusion_Initiate;
        localB.Infusion_Inhibit = rtu_OP_CMD_IN.Infusion_Inhibit;
        localB.Infusion_Cancel = rtu_OP_CMD_IN.Infusion_Cancel;

        /* BusSelector: '<Root>/BusConversion_InsertedFor_PATIENT_IN_at_outport_0' */
        localB.Patient_Bolus_Request = rtu_PATIENT_IN.Patient_Bolus_Request;

        /* BusSelector: '<Root>/BusConversion_InsertedFor_SYS_STAT_IN_at_outport_0' */
        localB.Reservoir_Empty = rtu_SYS_STAT_IN.Reservoir_Empty;
        localB.Volume_Infused = rtu_SYS_STAT_IN.Volume_Infused;

        /* Chart: '<Root>/Infusion Manager Sub-System' incorporates:
         *  BusSelector: '<Root>/BusConversion_InsertedFor_TLM_MODE_IN_at_outport_0'
         */
        /* Gateway: Infusion Manager Sub-System */
        /* During: Infusion Manager Sub-System */
        //DB_prinTF("10: ");
        if (localDW.is_active_c2_INFUSION_MGR_Functional == 0) {
            /* Entry: Infusion Manager Sub-System */
            //DB_prinTF("11: ");
            localDW.is_active_c2_INFUSION_MGR_Functional = 1;

            /* Entry Internal: Infusion Manager Sub-System */
            if (rtu_TLM_MODE_IN.System_On) {
                /* Transition: '<S1>:3986' */
                localDW.is_c2_INFUSION_MGR_Functional =
                        INFUSION_MGR_Functional_IN_Infusion_Manager;

                /* Entry Internal 'Infusion_Manager': '<S1>:3858' */
                /* Transition: '<S1>:3860' */
                localDW.is_Infusion_Manager = INFUSION_MGR_Functional_IN_IDLE;

                /* Entry 'IDLE': '<S1>:3866' */
                localB.IM_OUT_Current_System_Mode = 1;
                localB.IM_OUT_Flow_Rate_Commanded = 0;
                INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);
            } else {
                /* Transition: '<S1>:3744' */
                localDW.is_c2_INFUSION_MGR_Functional = INFUSION_MGR_Functional_IN_NOT_ON;

                /* Entry 'NOT_ON': '<S1>:3740' */
                localB.IM_OUT_Current_System_Mode = 0;
                localB.IM_OUT_Flow_Rate_Commanded = 0;
            }
        } else if (localDW.is_c2_INFUSION_MGR_Functional ==
                INFUSION_MGR_Functional_IN_Infusion_Manager) {
            /* During 'Infusion_Manager': '<S1>:3858' */
            //DB_prinTF("12: ");
            if (!rtu_TLM_MODE_IN.System_On) {
                /* Transition: '<S1>:3732' */
                /* Exit Internal 'Infusion_Manager': '<S1>:3858' */

                if (localDW.is_Infusion_Manager == INFUSION_MGR_Functional_IN_IDLE) {
                    /* Exit 'IDLE': '<S1>:3866' */
                    localB.IM_OUT_Current_System_Mode = 1;
                    localB.IM_OUT_Flow_Rate_Commanded = 0;
                    INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);
                    localDW.is_Infusion_Manager =
                            INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
                } else if (localDW.is_Infusion_Manager == INFUSION_MGR_Functional_IN_THERAPY) {

                    /* Exit Internal 'THERAPY': '<S1>:3867' */
                    if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_ACTIVE) {
                        INFUSION_MGR_Functional_exit_internal_ACTIVE(localDW);
                        localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
                    } else if (localDW.is_THERAPY == INFUSION_MGR_Functional_IN_PAUSED) {
                        INFUSION_MGR_Functional_exit_internal_PAUSED(localB, localDW);
                        localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;

                    } else
                        localDW.is_THERAPY = INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;


                    /* Exit 'THERAPY': '<S1>:3867' */
                    INFUSION_MGR_Functional_TherapyExitOperations(localB);
                    localDW.is_Infusion_Manager =
                            INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;
                } else
                    localDW.is_Infusion_Manager =
                            INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD;


                localDW.is_c2_INFUSION_MGR_Functional = INFUSION_MGR_Functional_IN_NOT_ON;

                /* Entry 'NOT_ON': '<S1>:3740' */
                localB.IM_OUT_Current_System_Mode = 0;
                localB.IM_OUT_Flow_Rate_Commanded = 0;
            } else if (localDW.is_Infusion_Manager == INFUSION_MGR_Functional_IN_IDLE) {
                //DB_prinTF("13: ");
                //DB_prinTF("2: %d %d %d ",localB.Infusion_Initiate, localB.Configured ,localB.Reservoir_Empty);
                /* During 'IDLE': '<S1>:3866' */
                boolean Infusion_Cancel_l = localB.Infusion_Cancel;
                boolean Infusion_Inhibit_l = localB.Infusion_Inhibit;
                boolean Infusion_Initiate_l = localB.Infusion_Initiate;
                int Configured_l = localB.Configured;
                boolean Reservoir_Empty_l = localB.Reservoir_Empty;
                if (Infusion_Cancel_l || Infusion_Inhibit_l) {
                    //DB_prinTF("30: ");
                    /* Transition: '<S1>:3993' */
                    /* Exit 'IDLE': '<S1>:3866' */
                    localB.IM_OUT_Current_System_Mode = 1;
                    localB.IM_OUT_Flow_Rate_Commanded = 0;
                    INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);
                    localDW.is_Infusion_Manager = INFUSION_MGR_Functional_IN_IDLE;

                    /* Entry 'IDLE': '<S1>:3866' */
                    localB.IM_OUT_Current_System_Mode = 1;
                    localB.IM_OUT_Flow_Rate_Commanded = 0;
                    INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);
                } else if (Infusion_Initiate_l && (Configured_l > 0) && (!Reservoir_Empty_l)) {
                    //DB_prinTF("31: ");
                    /* Transition: '<S1>:3864' */
                    INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);

                    /* Transition: '<S1>:3863' */
                    localB.IM_OUT_New_Infusion = true;

                    /* Exit 'IDLE': '<S1>:3866' */
                    localB.IM_OUT_Current_System_Mode = 1;
                    localB.IM_OUT_Flow_Rate_Commanded = 0;
                    INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);
                    localDW.is_Infusion_Manager = INFUSION_MGR_Functional_IN_THERAPY;

                    /* Entry Internal 'THERAPY': '<S1>:3867' */
                    Infusion_Inhibit_l = localB.Infusion_Inhibit;
                    int Highest_Level_Alarm_l = localB.Highest_Level_Alarm;

                    if (Infusion_Inhibit_l || (Highest_Level_Alarm_l >= 3)) {
                        /* Transition: '<S1>:3994' */
                        localDW.is_THERAPY = INFUSION_MGR_Functional_IN_PAUSED;
                        INFUSION_MGR_Functional_enter_internal_PAUSED(localB, localDW);
                    } else {
                        /* Transition: '<S1>:3875' */
                        localDW.is_THERAPY = INFUSION_MGR_Functional_IN_ACTIVE;
                        INFUSION_MGR_Functional_enter_internal_ACTIVE(localB, localDW);
                    }
                } else {
                    //DB_prinTF("32: ");
                    localB.IM_OUT_Current_System_Mode = 1;
                    localB.IM_OUT_Flow_Rate_Commanded = 0;
                }
            } else {
                //DB_prinTF("14: ");
                INFUSION_MGR_Functional_THERAPY(localB, localDW);
            }
        } else {
            //DB_prinTF("20: ");
            /* During 'NOT_ON': '<S1>:3740' */
            if (rtu_TLM_MODE_IN.System_On) {
                /* Transition: '<S1>:3741' */
                /* Exit 'NOT_ON': '<S1>:3740' */
                localDW.is_c2_INFUSION_MGR_Functional =
                        INFUSION_MGR_Functional_IN_Infusion_Manager;

                /* Entry Internal 'Infusion_Manager': '<S1>:3858' */
                /* Transition: '<S1>:3860' */
                localDW.is_Infusion_Manager = INFUSION_MGR_Functional_IN_IDLE;

                /* Entry 'IDLE': '<S1>:3866' */
                localB.IM_OUT_Current_System_Mode = 1;
                localB.IM_OUT_Flow_Rate_Commanded = 0;
                INFUSION_MGR_Functional_resetAllInfusionDetails(localB, localDW);
            } else {
                localB.IM_OUT_Current_System_Mode = 0;
                localB.IM_OUT_Flow_Rate_Commanded = 0;
            }
        }

        /* End of Chart: '<Root>/Infusion Manager Sub-System' */

        /* BusCreator: '<Root>/BusConversion_InsertedFor_IM_OUT_at_inport_0' */
        rty_IM_OUT.Commanded_Flow_Rate = localB.IM_OUT_Flow_Rate_Commanded;
        rty_IM_OUT.Current_System_Mode = localB.IM_OUT_Current_System_Mode;
        rty_IM_OUT.New_Infusion = localB.IM_OUT_New_Infusion;
        rty_IM_OUT.Log_Message_ID = localB.IM_OUT_Log_Message_ID;
        rty_IM_OUT.Actual_Infusion_Duration = localB.IM_OUT_Actual_Infusion_Duration;
    }


    static private void INFUSION_MGR_FunctionalSymWrapper(
            //Inputs of Top_Level_Mode_Outputs rtu_TLM_MODE_IN
            boolean System_On,
            boolean Request_Confirm_Stop,
            int Log_Message_ID1,

            //Operator_Commands rtu_OP_CMD_IN
            boolean System_Start,
            boolean System_Stop,
            boolean Infusion_Initiate,
            boolean Infusion_Inhibit,
            boolean Infusion_Cancel,
            boolean Data_Config,
            boolean Next,
            boolean Back,
            boolean Cancel,
            boolean Keyboard,
            int Disable_Audio,
            boolean Notification_Cancel,
            int Configuration_Type,
            boolean Confirm_Stop,
            boolean Patient_Bolus_Request,
            int Patient_ID,
            int Drug_Name,
            int Drug_Concentration,
            int Infusion_Total_Duration,
            int VTBI_Total,
            int Flow_Rate_Basal,
            int Flow_Rate_Intermittent_Bolus,
            int Duration_Intermittent_Bolus,
            int Interval_Intermittent_Bolus,
            int Flow_Rate_Patient_Bolus,
            int Duration_Patient_Bolus,
            int Lockout_Period_Patient_Bolus,
            int Max_Number_of_Patient_Bolus,
            int Flow_Rate_KVO,
            int Entered_Reservoir_Volume,
            int Reservoir_Volume,
            int Configured,
            int Error_Message_ID,
            boolean Request_Config_Type,
            boolean Request_Confirm_Infusion_Initiate,
            boolean Request_Patient_Drug_Info,
            boolean Request_Infusion_Info,
            int Log_Message_ID,
            int Config_Timer,
            int Config_Mode,

            //Alarm_Outputs rtu_ALARM_IN
            int Is_Audio_Disabled,
            int Notification_Message,
            int Audio_Notification_Command,
            int Highest_Level_Alarm,
            int Log_Message_ID3,

            //System_Status_Outputs rtu_SYS_STAT_IN
            boolean Reservoir_Empty,
            int Reservoir_Volume2,
            int Volume_Infused,
            int Log_Message_ID2,
            boolean In_Therapy,

            //Infusion_Manager_Outputs rty_IM_OUT
            int Commanded_Flow_Rate,
            int Current_System_Mode,
            boolean New_Infusion,
            int Log_Message_ID4,
            int Actual_Infusion_Duration,

            //B_INFUSION_MGR_Functional_c_T localB
            int Highest_Level_Alarm2,
            int Infusion_Total_Duration2,
            int VTBI_Total2,
            int Flow_Rate_Basal2,
            int Flow_Rate_Intermittent_Bolus2,
            int Duration_Intermittent_Bolus2,
            int Interval_Intermittent_Bolus2,
            int Flow_Rate_Patient_Bolus2,
            int Duration_Patient_Bolus2,
            int Lockout_Period_Patient_Bolus2,
            int Max_Number_of_Patient_Bolus2,
            int Flow_Rate_KVO2,
            int Configured2,
            int Volume_Infused2,
            int IM_OUT_Flow_Rate_Commanded2,  /* '<Root>/Infusion Manager Sub-System' */
            int IM_OUT_Current_System_Mode2,  /* '<Root>/Infusion Manager Sub-System' */
            int IM_OUT_Log_Message_ID2,       /* '<Root>/Infusion Manager Sub-System' */
            int IM_OUT_Actual_Infusion_Duration2,/* '<Root>/Infusion Manager Sub-System' */
            boolean Infusion_Initiate2,
            boolean Infusion_Inhibit2,
            boolean Infusion_Cancel2,
            boolean Patient_Bolus_Request2,
            boolean Reservoir_Empty2,
            boolean IM_OUT_New_Infusion2,       /* '<Root>/Infusion Manager Sub-System' */

            //DW_INFUSION_MGR_Functional_f_T localDW
            int is_active_c2_INFUSION_MGR_Functional,/* '<Root>/Infusion Manager Sub-System' */
            int is_c2_INFUSION_MGR_Functional,/* '<Root>/Infusion Manager Sub-System' */
            int is_Infusion_Manager,         /* '<Root>/Infusion Manager Sub-System' */
            int is_THERAPY,                  /* '<Root>/Infusion Manager Sub-System' */
            int is_Arbiter,                  /* '<Root>/Infusion Manager Sub-System' */
            int is_active_Arbiter,           /* '<Root>/Infusion Manager Sub-System' */
            int is_Alarm_Paused,             /* '<Root>/Infusion Manager Sub-System' */
            int is_active_Alarm_Paused,      /* '<Root>/Infusion Manager Sub-System' */
            int is_Manual_Paused,            /* '<Root>/Infusion Manager Sub-System' */
            int is_active_Manual_Paused,     /* '<Root>/Infusion Manager Sub-System' */
            int is_BASAL,                    /* '<Root>/Infusion Manager Sub-System' */
            int is_active_BASAL,             /* '<Root>/Infusion Manager Sub-System' */
            int is_Arbiter_d,                /* '<Root>/Infusion Manager Sub-System' */
            int is_active_Arbiter_c,         /* '<Root>/Infusion Manager Sub-System' */
            int is_PATIENT,                  /* '<Root>/Infusion Manager Sub-System' */
            int is_active_PATIENT,           /* '<Root>/Infusion Manager Sub-System' */
            int is_INTERMITTENT,             /* '<Root>/Infusion Manager Sub-System' */
            int is_active_INTERMITTENT,      /* '<Root>/Infusion Manager Sub-System' */
            int sbolus_timer,                /* '<Root>/Infusion Manager Sub-System' */
            int pbolus_timer,                /* '<Root>/Infusion Manager Sub-System' */
            int lock_timer,                  /* '<Root>/Infusion Manager Sub-System' */
            int number_pbolus,               /* '<Root>/Infusion Manager Sub-System' */
            int sbolusInter_timer,           /* '<Root>/Infusion Manager Sub-System' */
            boolean sbolus_req,                /* '<Root>/Infusion Manager Sub-System' */
            boolean inPatientBolus,           /* '<Root>/Infusion Manager Sub-System' */
            boolean symVar
    ) {

        B_INFUSION_MGR_Functional_c_T localB = new B_INFUSION_MGR_Functional_c_T();
        localB.Highest_Level_Alarm = Highest_Level_Alarm2;
        localB.Infusion_Total_Duration = Infusion_Total_Duration2;
        localB.VTBI_Total = VTBI_Total2;
        localB.Flow_Rate_Basal = Flow_Rate_Basal2;
        localB.Flow_Rate_Intermittent_Bolus = Flow_Rate_Intermittent_Bolus2;
        localB.Duration_Intermittent_Bolus = Duration_Intermittent_Bolus2;
        localB.Interval_Intermittent_Bolus = Interval_Intermittent_Bolus2;
        localB.Flow_Rate_Patient_Bolus = Flow_Rate_Patient_Bolus2;
        localB.Duration_Patient_Bolus = Duration_Patient_Bolus2;
        localB.Lockout_Period_Patient_Bolus = Lockout_Period_Patient_Bolus2;
        localB.Max_Number_of_Patient_Bolus = Max_Number_of_Patient_Bolus2;
        localB.Flow_Rate_KVO = Flow_Rate_KVO2;
        localB.Configured = Configured2;
        localB.Volume_Infused = Volume_Infused2;
        localB.IM_OUT_Flow_Rate_Commanded = IM_OUT_Flow_Rate_Commanded2;  /* '<Root>/Infusion Manager Sub-System' */
        localB.IM_OUT_Current_System_Mode = IM_OUT_Current_System_Mode2;  /* '<Root>/Infusion Manager Sub-System' */
        localB.IM_OUT_Log_Message_ID = IM_OUT_Log_Message_ID2;       /* '<Root>/Infusion Manager Sub-System' */
        localB.IM_OUT_Actual_Infusion_Duration = IM_OUT_Actual_Infusion_Duration2;/* '<Root>/Infusion Manager Sub-System' */
        localB.Infusion_Initiate = Infusion_Initiate2;
        localB.Infusion_Inhibit = Infusion_Inhibit2;
        localB.Infusion_Cancel = Infusion_Cancel2;
        localB.Patient_Bolus_Request = Patient_Bolus_Request2;
        localB.Reservoir_Empty = Reservoir_Empty2;
        localB.IM_OUT_New_Infusion = IM_OUT_New_Infusion2;       /* '<Root>/Infusion Manager Sub-System' */
        DW_INFUSION_MGR_Functional_f_T localDW = new DW_INFUSION_MGR_Functional_f_T();
        localDW.is_active_c2_INFUSION_MGR_Functional = is_active_c2_INFUSION_MGR_Functional;/* '<Root>/Infusion Manager
         Sub-System' */
        localDW.is_c2_INFUSION_MGR_Functional = is_c2_INFUSION_MGR_Functional;/* '<Root>/Infusion Manager Sub-System' */
        localDW.is_Infusion_Manager = is_Infusion_Manager;         /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_THERAPY = is_THERAPY;                  /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_Arbiter = is_Arbiter;                  /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_active_Arbiter = is_active_Arbiter;           /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_Alarm_Paused = is_Alarm_Paused;             /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_active_Alarm_Paused = is_active_Alarm_Paused;      /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_Manual_Paused = is_Manual_Paused;            /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_active_Manual_Paused = is_active_Manual_Paused;     /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_BASAL = is_BASAL;                    /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_active_BASAL = is_active_BASAL;             /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_Arbiter_d = is_Arbiter_d;                /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_active_Arbiter_c = is_active_Arbiter_c;         /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_PATIENT = is_PATIENT;                  /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_active_PATIENT = is_active_PATIENT;           /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_INTERMITTENT = is_INTERMITTENT;             /* '<Root>/Infusion Manager Sub-System' */
        localDW.is_active_INTERMITTENT = is_active_INTERMITTENT;      /* '<Root>/Infusion Manager Sub-System' */
        localDW.sbolus_timer = sbolus_timer;                /* '<Root>/Infusion Manager Sub-System' */
        localDW.pbolus_timer = pbolus_timer;                /* '<Root>/Infusion Manager Sub-System' */
        localDW.lock_timer = lock_timer;                  /* '<Root>/Infusion Manager Sub-System' */
        localDW.number_pbolus = number_pbolus;               /* '<Root>/Infusion Manager Sub-System' */
        localDW.sbolusInter_timer = sbolusInter_timer;           /* '<Root>/Infusion Manager Sub-System' */
        localDW.sbolus_req = sbolus_req;                /* '<Root>/Infusion Manager Sub-System' */
        localDW.inPatientBolus = inPatientBolus;            /* '<Root>/Infusion Manager Sub-System' */

        Top_Level_Mode_Outputs rtu_TLM_MODE_IN = new Top_Level_Mode_Outputs();
        rtu_TLM_MODE_IN.System_On = System_On;
        rtu_TLM_MODE_IN.Request_Confirm_Stop = Request_Confirm_Stop;
        rtu_TLM_MODE_IN.Log_Message_ID = Log_Message_ID1;

        Operator_Commands rtu_OP_CMD_IN = new Operator_Commands();
        rtu_OP_CMD_IN.System_Start = System_Start;
        rtu_OP_CMD_IN.System_Stop = System_Stop;
        rtu_OP_CMD_IN.Infusion_Initiate = Infusion_Initiate;
        rtu_OP_CMD_IN.Infusion_Inhibit = Infusion_Inhibit;
        rtu_OP_CMD_IN.Infusion_Cancel = Infusion_Cancel;
        rtu_OP_CMD_IN.Data_Config = Data_Config;
        rtu_OP_CMD_IN.Next = Next;
        rtu_OP_CMD_IN.Back = Back;
        rtu_OP_CMD_IN.Cancel = Cancel;
        rtu_OP_CMD_IN.Keyboard = Keyboard;
        rtu_OP_CMD_IN.Disable_Audio = Disable_Audio;
        rtu_OP_CMD_IN.Notification_Cancel = Notification_Cancel;
        rtu_OP_CMD_IN.Configuration_Type = Configuration_Type;
        rtu_OP_CMD_IN.Confirm_Stop = Confirm_Stop;


        Patient_Inputs rtu_PATIENT_IN = new Patient_Inputs();
        rtu_PATIENT_IN.Patient_Bolus_Request = Patient_Bolus_Request;

        Config_Outputs rtu_CONFIG_IN = new Config_Outputs();

        rtu_CONFIG_IN.Patient_ID = Patient_ID;
        rtu_CONFIG_IN.Drug_Name = Drug_Name;
        rtu_CONFIG_IN.Drug_Concentration = Drug_Concentration;
        rtu_CONFIG_IN.Infusion_Total_Duration = Infusion_Total_Duration;
        rtu_CONFIG_IN.VTBI_Total = VTBI_Total;
        rtu_CONFIG_IN.Flow_Rate_Basal = Flow_Rate_Basal;
        rtu_CONFIG_IN.Flow_Rate_Intermittent_Bolus = Flow_Rate_Intermittent_Bolus;
        rtu_CONFIG_IN.Duration_Intermittent_Bolus = Duration_Intermittent_Bolus;
        rtu_CONFIG_IN.Interval_Intermittent_Bolus = Interval_Intermittent_Bolus;
        rtu_CONFIG_IN.Flow_Rate_Patient_Bolus = Flow_Rate_Patient_Bolus;
        rtu_CONFIG_IN.Duration_Patient_Bolus = Duration_Patient_Bolus;
        rtu_CONFIG_IN.Lockout_Period_Patient_Bolus = Lockout_Period_Patient_Bolus;
        rtu_CONFIG_IN.Max_Number_of_Patient_Bolus = Max_Number_of_Patient_Bolus;
        rtu_CONFIG_IN.Flow_Rate_KVO = Flow_Rate_KVO;
        rtu_CONFIG_IN.Entered_Reservoir_Volume = Entered_Reservoir_Volume;
        rtu_CONFIG_IN.Reservoir_Volume = Reservoir_Volume;
        rtu_CONFIG_IN.Configured = Configured;
        rtu_CONFIG_IN.Error_Message_ID = Error_Message_ID;
        rtu_CONFIG_IN.Request_Config_Type = Request_Config_Type;
        rtu_CONFIG_IN.Request_Confirm_Infusion_Initiate = Request_Confirm_Infusion_Initiate;
        rtu_CONFIG_IN.Request_Patient_Drug_Info = Request_Patient_Drug_Info;
        rtu_CONFIG_IN.Request_Infusion_Info = Request_Infusion_Info;
        rtu_CONFIG_IN.Log_Message_ID = Log_Message_ID;
        rtu_CONFIG_IN.Config_Timer = Config_Timer;
        rtu_CONFIG_IN.Config_Mode = Config_Mode;

        Alarm_Outputs rtu_ALARM_IN = new Alarm_Outputs();
        rtu_ALARM_IN.Is_Audio_Disabled = Is_Audio_Disabled;
        rtu_ALARM_IN.Notification_Message = Notification_Message;
        rtu_ALARM_IN.Audio_Notification_Command = Audio_Notification_Command;
        rtu_ALARM_IN.Highest_Level_Alarm = Highest_Level_Alarm;
        rtu_ALARM_IN.Log_Message_ID = Log_Message_ID3;

        System_Status_Outputs rtu_SYS_STAT_IN = new System_Status_Outputs();
        rtu_SYS_STAT_IN.Reservoir_Empty = Reservoir_Empty;
        rtu_SYS_STAT_IN.Reservoir_Volume = Reservoir_Volume2;
        rtu_SYS_STAT_IN.Volume_Infused = Volume_Infused;
        rtu_SYS_STAT_IN.Log_Message_ID = Log_Message_ID2;
        rtu_SYS_STAT_IN.In_Therapy = In_Therapy;

        Infusion_Manager_Outputs rty_IM_OUT = new Infusion_Manager_Outputs();
        rty_IM_OUT.Commanded_Flow_Rate = Commanded_Flow_Rate;
        rty_IM_OUT.Current_System_Mode = Current_System_Mode;
        rty_IM_OUT.New_Infusion = New_Infusion;
        rty_IM_OUT.Log_Message_ID = Log_Message_ID4;
        rty_IM_OUT.Actual_Infusion_Duration = Actual_Infusion_Duration;

        if (symVar) {
            //INFUSION_MGR_Functional_Init(localB, localDW);
            INFUSION_MGR_Functional(rtu_TLM_MODE_IN,
                    rtu_OP_CMD_IN, rtu_PATIENT_IN,
                    rtu_CONFIG_IN, rtu_ALARM_IN,
                    rtu_SYS_STAT_IN, rty_IM_OUT,
                    localB, localDW);
        }

    }


    public static void main(String[] args) {
        INFUSION_MGR_FunctionalSymWrapper(false, false, 1, false, false, false, false, false, false, false, false,
                false, false, 1, false, 1, false, false, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, false, false, false, false
                , 1, 1, 1, 1, 1, 1, 1, 1, false, 1, 1, 1, false, 1, 1, false, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, false, false, false, false, false, false, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, false, false,
                false);

    }

}