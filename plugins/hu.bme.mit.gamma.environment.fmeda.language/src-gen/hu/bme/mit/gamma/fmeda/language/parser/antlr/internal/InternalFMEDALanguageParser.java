package hu.bme.mit.gamma.fmeda.language.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import hu.bme.mit.gamma.fmeda.language.services.FMEDALanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalFMEDALanguageParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_DOUBLE", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'package'", "'fmeda'", "'component'", "'['", "']'", "'{'", "'}'", "'interface'", "'port'", "':'", "';'", "'.'", "'this'", "'CH'", "'<->'", "','", "'subcomponent'", "'diagnostics'", "'diagnosed'", "'by'", "'with'", "'FM'", "'PFM'", "'FR'", "'='", "'FP'", "'->'"
    };
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int RULE_DOUBLE=5;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalFMEDALanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalFMEDALanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalFMEDALanguageParser.tokenNames; }
    public String getGrammarFileName() { return "InternalFMEDALanguage.g"; }



     	private FMEDALanguageGrammarAccess grammarAccess;

        public InternalFMEDALanguageParser(TokenStream input, FMEDALanguageGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "PackageDef";
       	}

       	@Override
       	protected FMEDALanguageGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRulePackageDef"
    // InternalFMEDALanguage.g:64:1: entryRulePackageDef returns [EObject current=null] : iv_rulePackageDef= rulePackageDef EOF ;
    public final EObject entryRulePackageDef() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackageDef = null;


        try {
            // InternalFMEDALanguage.g:64:51: (iv_rulePackageDef= rulePackageDef EOF )
            // InternalFMEDALanguage.g:65:2: iv_rulePackageDef= rulePackageDef EOF
            {
             newCompositeNode(grammarAccess.getPackageDefRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePackageDef=rulePackageDef();

            state._fsp--;

             current =iv_rulePackageDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePackageDef"


    // $ANTLR start "rulePackageDef"
    // InternalFMEDALanguage.g:71:1: rulePackageDef returns [EObject current=null] : (otherlv_0= 'package' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_components_2_0= ruleFMEDAComponentDef ) ) | ( (lv_diagnostics_3_0= ruleFMEDADiagnostics ) ) | ( (lv_interfaces_4_0= ruleInterfaceDef ) ) )* ) ;
    public final EObject rulePackageDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        EObject lv_components_2_0 = null;

        EObject lv_diagnostics_3_0 = null;

        EObject lv_interfaces_4_0 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:77:2: ( (otherlv_0= 'package' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_components_2_0= ruleFMEDAComponentDef ) ) | ( (lv_diagnostics_3_0= ruleFMEDADiagnostics ) ) | ( (lv_interfaces_4_0= ruleInterfaceDef ) ) )* ) )
            // InternalFMEDALanguage.g:78:2: (otherlv_0= 'package' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_components_2_0= ruleFMEDAComponentDef ) ) | ( (lv_diagnostics_3_0= ruleFMEDADiagnostics ) ) | ( (lv_interfaces_4_0= ruleInterfaceDef ) ) )* )
            {
            // InternalFMEDALanguage.g:78:2: (otherlv_0= 'package' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_components_2_0= ruleFMEDAComponentDef ) ) | ( (lv_diagnostics_3_0= ruleFMEDADiagnostics ) ) | ( (lv_interfaces_4_0= ruleInterfaceDef ) ) )* )
            // InternalFMEDALanguage.g:79:3: otherlv_0= 'package' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_components_2_0= ruleFMEDAComponentDef ) ) | ( (lv_diagnostics_3_0= ruleFMEDADiagnostics ) ) | ( (lv_interfaces_4_0= ruleInterfaceDef ) ) )*
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getPackageDefAccess().getPackageKeyword_0());
            		
            // InternalFMEDALanguage.g:83:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFMEDALanguage.g:84:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFMEDALanguage.g:84:4: (lv_name_1_0= RULE_ID )
            // InternalFMEDALanguage.g:85:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getPackageDefAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPackageDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalFMEDALanguage.g:101:3: ( ( (lv_components_2_0= ruleFMEDAComponentDef ) ) | ( (lv_diagnostics_3_0= ruleFMEDADiagnostics ) ) | ( (lv_interfaces_4_0= ruleInterfaceDef ) ) )*
            loop1:
            do {
                int alt1=4;
                switch ( input.LA(1) ) {
                case 13:
                    {
                    alt1=1;
                    }
                    break;
                case 29:
                    {
                    alt1=2;
                    }
                    break;
                case 19:
                    {
                    alt1=3;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // InternalFMEDALanguage.g:102:4: ( (lv_components_2_0= ruleFMEDAComponentDef ) )
            	    {
            	    // InternalFMEDALanguage.g:102:4: ( (lv_components_2_0= ruleFMEDAComponentDef ) )
            	    // InternalFMEDALanguage.g:103:5: (lv_components_2_0= ruleFMEDAComponentDef )
            	    {
            	    // InternalFMEDALanguage.g:103:5: (lv_components_2_0= ruleFMEDAComponentDef )
            	    // InternalFMEDALanguage.g:104:6: lv_components_2_0= ruleFMEDAComponentDef
            	    {

            	    						newCompositeNode(grammarAccess.getPackageDefAccess().getComponentsFMEDAComponentDefParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_components_2_0=ruleFMEDAComponentDef();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPackageDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"components",
            	    							lv_components_2_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FMEDAComponentDef");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalFMEDALanguage.g:122:4: ( (lv_diagnostics_3_0= ruleFMEDADiagnostics ) )
            	    {
            	    // InternalFMEDALanguage.g:122:4: ( (lv_diagnostics_3_0= ruleFMEDADiagnostics ) )
            	    // InternalFMEDALanguage.g:123:5: (lv_diagnostics_3_0= ruleFMEDADiagnostics )
            	    {
            	    // InternalFMEDALanguage.g:123:5: (lv_diagnostics_3_0= ruleFMEDADiagnostics )
            	    // InternalFMEDALanguage.g:124:6: lv_diagnostics_3_0= ruleFMEDADiagnostics
            	    {

            	    						newCompositeNode(grammarAccess.getPackageDefAccess().getDiagnosticsFMEDADiagnosticsParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_diagnostics_3_0=ruleFMEDADiagnostics();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPackageDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"diagnostics",
            	    							lv_diagnostics_3_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FMEDADiagnostics");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalFMEDALanguage.g:142:4: ( (lv_interfaces_4_0= ruleInterfaceDef ) )
            	    {
            	    // InternalFMEDALanguage.g:142:4: ( (lv_interfaces_4_0= ruleInterfaceDef ) )
            	    // InternalFMEDALanguage.g:143:5: (lv_interfaces_4_0= ruleInterfaceDef )
            	    {
            	    // InternalFMEDALanguage.g:143:5: (lv_interfaces_4_0= ruleInterfaceDef )
            	    // InternalFMEDALanguage.g:144:6: lv_interfaces_4_0= ruleInterfaceDef
            	    {

            	    						newCompositeNode(grammarAccess.getPackageDefAccess().getInterfacesInterfaceDefParserRuleCall_2_2_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_interfaces_4_0=ruleInterfaceDef();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPackageDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"interfaces",
            	    							lv_interfaces_4_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.InterfaceDef");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePackageDef"


    // $ANTLR start "entryRuleFMEDAComponentDef"
    // InternalFMEDALanguage.g:166:1: entryRuleFMEDAComponentDef returns [EObject current=null] : iv_ruleFMEDAComponentDef= ruleFMEDAComponentDef EOF ;
    public final EObject entryRuleFMEDAComponentDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFMEDAComponentDef = null;


        try {
            // InternalFMEDALanguage.g:166:58: (iv_ruleFMEDAComponentDef= ruleFMEDAComponentDef EOF )
            // InternalFMEDALanguage.g:167:2: iv_ruleFMEDAComponentDef= ruleFMEDAComponentDef EOF
            {
             newCompositeNode(grammarAccess.getFMEDAComponentDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFMEDAComponentDef=ruleFMEDAComponentDef();

            state._fsp--;

             current =iv_ruleFMEDAComponentDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFMEDAComponentDef"


    // $ANTLR start "ruleFMEDAComponentDef"
    // InternalFMEDALanguage.g:173:1: ruleFMEDAComponentDef returns [EObject current=null] : (otherlv_0= 'fmeda' otherlv_1= 'component' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_ports_4_0= rulePort ) )* otherlv_5= ']' )? otherlv_6= '{' ( ( (lv_failuremodes_7_0= ruleFailureModeDef ) ) | ( (lv_failuremodes_8_0= rulePartFailureModeDef ) ) | ( (lv_subcomponents_9_0= ruleFMEDAComponentInstance ) ) | ( (lv_failurepropagations_10_0= ruleFailurePropagationDef ) ) | ( (lv_channels_11_0= ruleChannel ) ) )* otherlv_12= '}' ) ;
    public final EObject ruleFMEDAComponentDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_12=null;
        EObject lv_ports_4_0 = null;

        EObject lv_failuremodes_7_0 = null;

        EObject lv_failuremodes_8_0 = null;

        EObject lv_subcomponents_9_0 = null;

        EObject lv_failurepropagations_10_0 = null;

        EObject lv_channels_11_0 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:179:2: ( (otherlv_0= 'fmeda' otherlv_1= 'component' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_ports_4_0= rulePort ) )* otherlv_5= ']' )? otherlv_6= '{' ( ( (lv_failuremodes_7_0= ruleFailureModeDef ) ) | ( (lv_failuremodes_8_0= rulePartFailureModeDef ) ) | ( (lv_subcomponents_9_0= ruleFMEDAComponentInstance ) ) | ( (lv_failurepropagations_10_0= ruleFailurePropagationDef ) ) | ( (lv_channels_11_0= ruleChannel ) ) )* otherlv_12= '}' ) )
            // InternalFMEDALanguage.g:180:2: (otherlv_0= 'fmeda' otherlv_1= 'component' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_ports_4_0= rulePort ) )* otherlv_5= ']' )? otherlv_6= '{' ( ( (lv_failuremodes_7_0= ruleFailureModeDef ) ) | ( (lv_failuremodes_8_0= rulePartFailureModeDef ) ) | ( (lv_subcomponents_9_0= ruleFMEDAComponentInstance ) ) | ( (lv_failurepropagations_10_0= ruleFailurePropagationDef ) ) | ( (lv_channels_11_0= ruleChannel ) ) )* otherlv_12= '}' )
            {
            // InternalFMEDALanguage.g:180:2: (otherlv_0= 'fmeda' otherlv_1= 'component' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_ports_4_0= rulePort ) )* otherlv_5= ']' )? otherlv_6= '{' ( ( (lv_failuremodes_7_0= ruleFailureModeDef ) ) | ( (lv_failuremodes_8_0= rulePartFailureModeDef ) ) | ( (lv_subcomponents_9_0= ruleFMEDAComponentInstance ) ) | ( (lv_failurepropagations_10_0= ruleFailurePropagationDef ) ) | ( (lv_channels_11_0= ruleChannel ) ) )* otherlv_12= '}' )
            // InternalFMEDALanguage.g:181:3: otherlv_0= 'fmeda' otherlv_1= 'component' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_ports_4_0= rulePort ) )* otherlv_5= ']' )? otherlv_6= '{' ( ( (lv_failuremodes_7_0= ruleFailureModeDef ) ) | ( (lv_failuremodes_8_0= rulePartFailureModeDef ) ) | ( (lv_subcomponents_9_0= ruleFMEDAComponentInstance ) ) | ( (lv_failurepropagations_10_0= ruleFailurePropagationDef ) ) | ( (lv_channels_11_0= ruleChannel ) ) )* otherlv_12= '}'
            {
            otherlv_0=(Token)match(input,13,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getFMEDAComponentDefAccess().getFmedaKeyword_0());
            		
            otherlv_1=(Token)match(input,14,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFMEDAComponentDefAccess().getComponentKeyword_1());
            		
            // InternalFMEDALanguage.g:189:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalFMEDALanguage.g:190:4: (lv_name_2_0= RULE_ID )
            {
            // InternalFMEDALanguage.g:190:4: (lv_name_2_0= RULE_ID )
            // InternalFMEDALanguage.g:191:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_name_2_0, grammarAccess.getFMEDAComponentDefAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFMEDAComponentDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalFMEDALanguage.g:207:3: (otherlv_3= '[' ( (lv_ports_4_0= rulePort ) )* otherlv_5= ']' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalFMEDALanguage.g:208:4: otherlv_3= '[' ( (lv_ports_4_0= rulePort ) )* otherlv_5= ']'
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_7); 

                    				newLeafNode(otherlv_3, grammarAccess.getFMEDAComponentDefAccess().getLeftSquareBracketKeyword_3_0());
                    			
                    // InternalFMEDALanguage.g:212:4: ( (lv_ports_4_0= rulePort ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==20) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // InternalFMEDALanguage.g:213:5: (lv_ports_4_0= rulePort )
                    	    {
                    	    // InternalFMEDALanguage.g:213:5: (lv_ports_4_0= rulePort )
                    	    // InternalFMEDALanguage.g:214:6: lv_ports_4_0= rulePort
                    	    {

                    	    						newCompositeNode(grammarAccess.getFMEDAComponentDefAccess().getPortsPortParserRuleCall_3_1_0());
                    	    					
                    	    pushFollow(FOLLOW_7);
                    	    lv_ports_4_0=rulePort();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getFMEDAComponentDefRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"ports",
                    	    							lv_ports_4_0,
                    	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.Port");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,16,FOLLOW_8); 

                    				newLeafNode(otherlv_5, grammarAccess.getFMEDAComponentDefAccess().getRightSquareBracketKeyword_3_2());
                    			

                    }
                    break;

            }

            otherlv_6=(Token)match(input,17,FOLLOW_9); 

            			newLeafNode(otherlv_6, grammarAccess.getFMEDAComponentDefAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalFMEDALanguage.g:240:3: ( ( (lv_failuremodes_7_0= ruleFailureModeDef ) ) | ( (lv_failuremodes_8_0= rulePartFailureModeDef ) ) | ( (lv_subcomponents_9_0= ruleFMEDAComponentInstance ) ) | ( (lv_failurepropagations_10_0= ruleFailurePropagationDef ) ) | ( (lv_channels_11_0= ruleChannel ) ) )*
            loop4:
            do {
                int alt4=6;
                switch ( input.LA(1) ) {
                case 33:
                    {
                    alt4=1;
                    }
                    break;
                case 34:
                    {
                    alt4=2;
                    }
                    break;
                case 28:
                    {
                    alt4=3;
                    }
                    break;
                case 37:
                    {
                    alt4=4;
                    }
                    break;
                case 25:
                    {
                    alt4=5;
                    }
                    break;

                }

                switch (alt4) {
            	case 1 :
            	    // InternalFMEDALanguage.g:241:4: ( (lv_failuremodes_7_0= ruleFailureModeDef ) )
            	    {
            	    // InternalFMEDALanguage.g:241:4: ( (lv_failuremodes_7_0= ruleFailureModeDef ) )
            	    // InternalFMEDALanguage.g:242:5: (lv_failuremodes_7_0= ruleFailureModeDef )
            	    {
            	    // InternalFMEDALanguage.g:242:5: (lv_failuremodes_7_0= ruleFailureModeDef )
            	    // InternalFMEDALanguage.g:243:6: lv_failuremodes_7_0= ruleFailureModeDef
            	    {

            	    						newCompositeNode(grammarAccess.getFMEDAComponentDefAccess().getFailuremodesFailureModeDefParserRuleCall_5_0_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_failuremodes_7_0=ruleFailureModeDef();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFMEDAComponentDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"failuremodes",
            	    							lv_failuremodes_7_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FailureModeDef");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalFMEDALanguage.g:261:4: ( (lv_failuremodes_8_0= rulePartFailureModeDef ) )
            	    {
            	    // InternalFMEDALanguage.g:261:4: ( (lv_failuremodes_8_0= rulePartFailureModeDef ) )
            	    // InternalFMEDALanguage.g:262:5: (lv_failuremodes_8_0= rulePartFailureModeDef )
            	    {
            	    // InternalFMEDALanguage.g:262:5: (lv_failuremodes_8_0= rulePartFailureModeDef )
            	    // InternalFMEDALanguage.g:263:6: lv_failuremodes_8_0= rulePartFailureModeDef
            	    {

            	    						newCompositeNode(grammarAccess.getFMEDAComponentDefAccess().getFailuremodesPartFailureModeDefParserRuleCall_5_1_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_failuremodes_8_0=rulePartFailureModeDef();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFMEDAComponentDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"failuremodes",
            	    							lv_failuremodes_8_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.PartFailureModeDef");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalFMEDALanguage.g:281:4: ( (lv_subcomponents_9_0= ruleFMEDAComponentInstance ) )
            	    {
            	    // InternalFMEDALanguage.g:281:4: ( (lv_subcomponents_9_0= ruleFMEDAComponentInstance ) )
            	    // InternalFMEDALanguage.g:282:5: (lv_subcomponents_9_0= ruleFMEDAComponentInstance )
            	    {
            	    // InternalFMEDALanguage.g:282:5: (lv_subcomponents_9_0= ruleFMEDAComponentInstance )
            	    // InternalFMEDALanguage.g:283:6: lv_subcomponents_9_0= ruleFMEDAComponentInstance
            	    {

            	    						newCompositeNode(grammarAccess.getFMEDAComponentDefAccess().getSubcomponentsFMEDAComponentInstanceParserRuleCall_5_2_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_subcomponents_9_0=ruleFMEDAComponentInstance();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFMEDAComponentDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"subcomponents",
            	    							lv_subcomponents_9_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FMEDAComponentInstance");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalFMEDALanguage.g:301:4: ( (lv_failurepropagations_10_0= ruleFailurePropagationDef ) )
            	    {
            	    // InternalFMEDALanguage.g:301:4: ( (lv_failurepropagations_10_0= ruleFailurePropagationDef ) )
            	    // InternalFMEDALanguage.g:302:5: (lv_failurepropagations_10_0= ruleFailurePropagationDef )
            	    {
            	    // InternalFMEDALanguage.g:302:5: (lv_failurepropagations_10_0= ruleFailurePropagationDef )
            	    // InternalFMEDALanguage.g:303:6: lv_failurepropagations_10_0= ruleFailurePropagationDef
            	    {

            	    						newCompositeNode(grammarAccess.getFMEDAComponentDefAccess().getFailurepropagationsFailurePropagationDefParserRuleCall_5_3_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_failurepropagations_10_0=ruleFailurePropagationDef();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFMEDAComponentDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"failurepropagations",
            	    							lv_failurepropagations_10_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FailurePropagationDef");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalFMEDALanguage.g:321:4: ( (lv_channels_11_0= ruleChannel ) )
            	    {
            	    // InternalFMEDALanguage.g:321:4: ( (lv_channels_11_0= ruleChannel ) )
            	    // InternalFMEDALanguage.g:322:5: (lv_channels_11_0= ruleChannel )
            	    {
            	    // InternalFMEDALanguage.g:322:5: (lv_channels_11_0= ruleChannel )
            	    // InternalFMEDALanguage.g:323:6: lv_channels_11_0= ruleChannel
            	    {

            	    						newCompositeNode(grammarAccess.getFMEDAComponentDefAccess().getChannelsChannelParserRuleCall_5_4_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_channels_11_0=ruleChannel();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFMEDAComponentDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"channels",
            	    							lv_channels_11_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.Channel");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            otherlv_12=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_12, grammarAccess.getFMEDAComponentDefAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFMEDAComponentDef"


    // $ANTLR start "entryRuleInterfaceDef"
    // InternalFMEDALanguage.g:349:1: entryRuleInterfaceDef returns [EObject current=null] : iv_ruleInterfaceDef= ruleInterfaceDef EOF ;
    public final EObject entryRuleInterfaceDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceDef = null;


        try {
            // InternalFMEDALanguage.g:349:53: (iv_ruleInterfaceDef= ruleInterfaceDef EOF )
            // InternalFMEDALanguage.g:350:2: iv_ruleInterfaceDef= ruleInterfaceDef EOF
            {
             newCompositeNode(grammarAccess.getInterfaceDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInterfaceDef=ruleInterfaceDef();

            state._fsp--;

             current =iv_ruleInterfaceDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterfaceDef"


    // $ANTLR start "ruleInterfaceDef"
    // InternalFMEDALanguage.g:356:1: ruleInterfaceDef returns [EObject current=null] : (otherlv_0= 'interface' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_failuremodes_3_0= ruleFailureModeDef ) )+ otherlv_4= '}' ) ;
    public final EObject ruleInterfaceDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_failuremodes_3_0 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:362:2: ( (otherlv_0= 'interface' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_failuremodes_3_0= ruleFailureModeDef ) )+ otherlv_4= '}' ) )
            // InternalFMEDALanguage.g:363:2: (otherlv_0= 'interface' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_failuremodes_3_0= ruleFailureModeDef ) )+ otherlv_4= '}' )
            {
            // InternalFMEDALanguage.g:363:2: (otherlv_0= 'interface' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_failuremodes_3_0= ruleFailureModeDef ) )+ otherlv_4= '}' )
            // InternalFMEDALanguage.g:364:3: otherlv_0= 'interface' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_failuremodes_3_0= ruleFailureModeDef ) )+ otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getInterfaceDefAccess().getInterfaceKeyword_0());
            		
            // InternalFMEDALanguage.g:368:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFMEDALanguage.g:369:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFMEDALanguage.g:369:4: (lv_name_1_0= RULE_ID )
            // InternalFMEDALanguage.g:370:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(lv_name_1_0, grammarAccess.getInterfaceDefAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInterfaceDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_10); 

            			newLeafNode(otherlv_2, grammarAccess.getInterfaceDefAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalFMEDALanguage.g:390:3: ( (lv_failuremodes_3_0= ruleFailureModeDef ) )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==33) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalFMEDALanguage.g:391:4: (lv_failuremodes_3_0= ruleFailureModeDef )
            	    {
            	    // InternalFMEDALanguage.g:391:4: (lv_failuremodes_3_0= ruleFailureModeDef )
            	    // InternalFMEDALanguage.g:392:5: lv_failuremodes_3_0= ruleFailureModeDef
            	    {

            	    					newCompositeNode(grammarAccess.getInterfaceDefAccess().getFailuremodesFailureModeDefParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_11);
            	    lv_failuremodes_3_0=ruleFailureModeDef();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getInterfaceDefRule());
            	    					}
            	    					add(
            	    						current,
            	    						"failuremodes",
            	    						lv_failuremodes_3_0,
            	    						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FailureModeDef");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            otherlv_4=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getInterfaceDefAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInterfaceDef"


    // $ANTLR start "entryRulePort"
    // InternalFMEDALanguage.g:417:1: entryRulePort returns [EObject current=null] : iv_rulePort= rulePort EOF ;
    public final EObject entryRulePort() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePort = null;


        try {
            // InternalFMEDALanguage.g:417:45: (iv_rulePort= rulePort EOF )
            // InternalFMEDALanguage.g:418:2: iv_rulePort= rulePort EOF
            {
             newCompositeNode(grammarAccess.getPortRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePort=rulePort();

            state._fsp--;

             current =iv_rulePort; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePort"


    // $ANTLR start "rulePort"
    // InternalFMEDALanguage.g:424:1: rulePort returns [EObject current=null] : (otherlv_0= 'port' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';' ) ;
    public final EObject rulePort() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:430:2: ( (otherlv_0= 'port' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';' ) )
            // InternalFMEDALanguage.g:431:2: (otherlv_0= 'port' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';' )
            {
            // InternalFMEDALanguage.g:431:2: (otherlv_0= 'port' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';' )
            // InternalFMEDALanguage.g:432:3: otherlv_0= 'port' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';'
            {
            otherlv_0=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getPortAccess().getPortKeyword_0());
            		
            // InternalFMEDALanguage.g:436:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFMEDALanguage.g:437:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFMEDALanguage.g:437:4: (lv_name_1_0= RULE_ID )
            // InternalFMEDALanguage.g:438:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_12); 

            					newLeafNode(lv_name_1_0, grammarAccess.getPortAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPortRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getPortAccess().getColonKeyword_2());
            		
            // InternalFMEDALanguage.g:458:3: ( (otherlv_3= RULE_ID ) )
            // InternalFMEDALanguage.g:459:4: (otherlv_3= RULE_ID )
            {
            // InternalFMEDALanguage.g:459:4: (otherlv_3= RULE_ID )
            // InternalFMEDALanguage.g:460:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPortRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(otherlv_3, grammarAccess.getPortAccess().getTypeInterfaceCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getPortAccess().getSemicolonKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePort"


    // $ANTLR start "entryRuleChannelInterfaceReference"
    // InternalFMEDALanguage.g:479:1: entryRuleChannelInterfaceReference returns [EObject current=null] : iv_ruleChannelInterfaceReference= ruleChannelInterfaceReference EOF ;
    public final EObject entryRuleChannelInterfaceReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChannelInterfaceReference = null;


        try {
            // InternalFMEDALanguage.g:479:66: (iv_ruleChannelInterfaceReference= ruleChannelInterfaceReference EOF )
            // InternalFMEDALanguage.g:480:2: iv_ruleChannelInterfaceReference= ruleChannelInterfaceReference EOF
            {
             newCompositeNode(grammarAccess.getChannelInterfaceReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChannelInterfaceReference=ruleChannelInterfaceReference();

            state._fsp--;

             current =iv_ruleChannelInterfaceReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChannelInterfaceReference"


    // $ANTLR start "ruleChannelInterfaceReference"
    // InternalFMEDALanguage.g:486:1: ruleChannelInterfaceReference returns [EObject current=null] : (this_HardwarePartPortReference_0= ruleHardwarePartPortReference | this_ThisPortReference_1= ruleThisPortReference ) ;
    public final EObject ruleChannelInterfaceReference() throws RecognitionException {
        EObject current = null;

        EObject this_HardwarePartPortReference_0 = null;

        EObject this_ThisPortReference_1 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:492:2: ( (this_HardwarePartPortReference_0= ruleHardwarePartPortReference | this_ThisPortReference_1= ruleThisPortReference ) )
            // InternalFMEDALanguage.g:493:2: (this_HardwarePartPortReference_0= ruleHardwarePartPortReference | this_ThisPortReference_1= ruleThisPortReference )
            {
            // InternalFMEDALanguage.g:493:2: (this_HardwarePartPortReference_0= ruleHardwarePartPortReference | this_ThisPortReference_1= ruleThisPortReference )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==24) ) {
                    alt6=2;
                }
                else if ( (LA6_1==RULE_ID) ) {
                    alt6=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalFMEDALanguage.g:494:3: this_HardwarePartPortReference_0= ruleHardwarePartPortReference
                    {

                    			newCompositeNode(grammarAccess.getChannelInterfaceReferenceAccess().getHardwarePartPortReferenceParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_HardwarePartPortReference_0=ruleHardwarePartPortReference();

                    state._fsp--;


                    			current = this_HardwarePartPortReference_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFMEDALanguage.g:503:3: this_ThisPortReference_1= ruleThisPortReference
                    {

                    			newCompositeNode(grammarAccess.getChannelInterfaceReferenceAccess().getThisPortReferenceParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ThisPortReference_1=ruleThisPortReference();

                    state._fsp--;


                    			current = this_ThisPortReference_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChannelInterfaceReference"


    // $ANTLR start "entryRuleHardwarePartPortReference"
    // InternalFMEDALanguage.g:515:1: entryRuleHardwarePartPortReference returns [EObject current=null] : iv_ruleHardwarePartPortReference= ruleHardwarePartPortReference EOF ;
    public final EObject entryRuleHardwarePartPortReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHardwarePartPortReference = null;


        try {
            // InternalFMEDALanguage.g:515:66: (iv_ruleHardwarePartPortReference= ruleHardwarePartPortReference EOF )
            // InternalFMEDALanguage.g:516:2: iv_ruleHardwarePartPortReference= ruleHardwarePartPortReference EOF
            {
             newCompositeNode(grammarAccess.getHardwarePartPortReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHardwarePartPortReference=ruleHardwarePartPortReference();

            state._fsp--;

             current =iv_ruleHardwarePartPortReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHardwarePartPortReference"


    // $ANTLR start "ruleHardwarePartPortReference"
    // InternalFMEDALanguage.g:522:1: ruleHardwarePartPortReference returns [EObject current=null] : (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' ) ;
    public final EObject ruleHardwarePartPortReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:528:2: ( (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' ) )
            // InternalFMEDALanguage.g:529:2: (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' )
            {
            // InternalFMEDALanguage.g:529:2: (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' )
            // InternalFMEDALanguage.g:530:3: otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getHardwarePartPortReferenceAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalFMEDALanguage.g:534:3: ( (otherlv_1= RULE_ID ) )
            // InternalFMEDALanguage.g:535:4: (otherlv_1= RULE_ID )
            {
            // InternalFMEDALanguage.g:535:4: (otherlv_1= RULE_ID )
            // InternalFMEDALanguage.g:536:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getHardwarePartPortReferenceRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_14); 

            					newLeafNode(otherlv_1, grammarAccess.getHardwarePartPortReferenceAccess().getPartFMEDAComponentInstanceCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getHardwarePartPortReferenceAccess().getFullStopKeyword_2());
            		
            // InternalFMEDALanguage.g:551:3: ( (otherlv_3= RULE_ID ) )
            // InternalFMEDALanguage.g:552:4: (otherlv_3= RULE_ID )
            {
            // InternalFMEDALanguage.g:552:4: (otherlv_3= RULE_ID )
            // InternalFMEDALanguage.g:553:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getHardwarePartPortReferenceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_15); 

            					newLeafNode(otherlv_3, grammarAccess.getHardwarePartPortReferenceAccess().getPortPortCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getHardwarePartPortReferenceAccess().getRightSquareBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHardwarePartPortReference"


    // $ANTLR start "entryRuleThisPortReference"
    // InternalFMEDALanguage.g:572:1: entryRuleThisPortReference returns [EObject current=null] : iv_ruleThisPortReference= ruleThisPortReference EOF ;
    public final EObject entryRuleThisPortReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisPortReference = null;


        try {
            // InternalFMEDALanguage.g:572:58: (iv_ruleThisPortReference= ruleThisPortReference EOF )
            // InternalFMEDALanguage.g:573:2: iv_ruleThisPortReference= ruleThisPortReference EOF
            {
             newCompositeNode(grammarAccess.getThisPortReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleThisPortReference=ruleThisPortReference();

            state._fsp--;

             current =iv_ruleThisPortReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleThisPortReference"


    // $ANTLR start "ruleThisPortReference"
    // InternalFMEDALanguage.g:579:1: ruleThisPortReference returns [EObject current=null] : (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' ) ;
    public final EObject ruleThisPortReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:585:2: ( (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' ) )
            // InternalFMEDALanguage.g:586:2: (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' )
            {
            // InternalFMEDALanguage.g:586:2: (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' )
            // InternalFMEDALanguage.g:587:3: otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getThisPortReferenceAccess().getLeftSquareBracketKeyword_0());
            		
            otherlv_1=(Token)match(input,24,FOLLOW_14); 

            			newLeafNode(otherlv_1, grammarAccess.getThisPortReferenceAccess().getThisKeyword_1());
            		
            otherlv_2=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getThisPortReferenceAccess().getFullStopKeyword_2());
            		
            // InternalFMEDALanguage.g:599:3: ( (otherlv_3= RULE_ID ) )
            // InternalFMEDALanguage.g:600:4: (otherlv_3= RULE_ID )
            {
            // InternalFMEDALanguage.g:600:4: (otherlv_3= RULE_ID )
            // InternalFMEDALanguage.g:601:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getThisPortReferenceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_15); 

            					newLeafNode(otherlv_3, grammarAccess.getThisPortReferenceAccess().getPortPortCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getThisPortReferenceAccess().getRightSquareBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleThisPortReference"


    // $ANTLR start "entryRuleChannel"
    // InternalFMEDALanguage.g:620:1: entryRuleChannel returns [EObject current=null] : iv_ruleChannel= ruleChannel EOF ;
    public final EObject entryRuleChannel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChannel = null;


        try {
            // InternalFMEDALanguage.g:620:48: (iv_ruleChannel= ruleChannel EOF )
            // InternalFMEDALanguage.g:621:2: iv_ruleChannel= ruleChannel EOF
            {
             newCompositeNode(grammarAccess.getChannelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChannel=ruleChannel();

            state._fsp--;

             current =iv_ruleChannel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChannel"


    // $ANTLR start "ruleChannel"
    // InternalFMEDALanguage.g:627:1: ruleChannel returns [EObject current=null] : (otherlv_0= 'CH' ( (lv_from_1_0= ruleChannelInterfaceReference ) ) otherlv_2= '<->' ( ( (lv_to_3_0= ruleChannelInterfaceReference ) ) otherlv_4= ',' )* ( (lv_to_5_0= ruleChannelInterfaceReference ) ) otherlv_6= ';' ) ;
    public final EObject ruleChannel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_from_1_0 = null;

        EObject lv_to_3_0 = null;

        EObject lv_to_5_0 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:633:2: ( (otherlv_0= 'CH' ( (lv_from_1_0= ruleChannelInterfaceReference ) ) otherlv_2= '<->' ( ( (lv_to_3_0= ruleChannelInterfaceReference ) ) otherlv_4= ',' )* ( (lv_to_5_0= ruleChannelInterfaceReference ) ) otherlv_6= ';' ) )
            // InternalFMEDALanguage.g:634:2: (otherlv_0= 'CH' ( (lv_from_1_0= ruleChannelInterfaceReference ) ) otherlv_2= '<->' ( ( (lv_to_3_0= ruleChannelInterfaceReference ) ) otherlv_4= ',' )* ( (lv_to_5_0= ruleChannelInterfaceReference ) ) otherlv_6= ';' )
            {
            // InternalFMEDALanguage.g:634:2: (otherlv_0= 'CH' ( (lv_from_1_0= ruleChannelInterfaceReference ) ) otherlv_2= '<->' ( ( (lv_to_3_0= ruleChannelInterfaceReference ) ) otherlv_4= ',' )* ( (lv_to_5_0= ruleChannelInterfaceReference ) ) otherlv_6= ';' )
            // InternalFMEDALanguage.g:635:3: otherlv_0= 'CH' ( (lv_from_1_0= ruleChannelInterfaceReference ) ) otherlv_2= '<->' ( ( (lv_to_3_0= ruleChannelInterfaceReference ) ) otherlv_4= ',' )* ( (lv_to_5_0= ruleChannelInterfaceReference ) ) otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getChannelAccess().getCHKeyword_0());
            		
            // InternalFMEDALanguage.g:639:3: ( (lv_from_1_0= ruleChannelInterfaceReference ) )
            // InternalFMEDALanguage.g:640:4: (lv_from_1_0= ruleChannelInterfaceReference )
            {
            // InternalFMEDALanguage.g:640:4: (lv_from_1_0= ruleChannelInterfaceReference )
            // InternalFMEDALanguage.g:641:5: lv_from_1_0= ruleChannelInterfaceReference
            {

            					newCompositeNode(grammarAccess.getChannelAccess().getFromChannelInterfaceReferenceParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_18);
            lv_from_1_0=ruleChannelInterfaceReference();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getChannelRule());
            					}
            					set(
            						current,
            						"from",
            						lv_from_1_0,
            						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.ChannelInterfaceReference");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,26,FOLLOW_17); 

            			newLeafNode(otherlv_2, grammarAccess.getChannelAccess().getLessThanSignHyphenMinusGreaterThanSignKeyword_2());
            		
            // InternalFMEDALanguage.g:662:3: ( ( (lv_to_3_0= ruleChannelInterfaceReference ) ) otherlv_4= ',' )*
            loop7:
            do {
                int alt7=2;
                alt7 = dfa7.predict(input);
                switch (alt7) {
            	case 1 :
            	    // InternalFMEDALanguage.g:663:4: ( (lv_to_3_0= ruleChannelInterfaceReference ) ) otherlv_4= ','
            	    {
            	    // InternalFMEDALanguage.g:663:4: ( (lv_to_3_0= ruleChannelInterfaceReference ) )
            	    // InternalFMEDALanguage.g:664:5: (lv_to_3_0= ruleChannelInterfaceReference )
            	    {
            	    // InternalFMEDALanguage.g:664:5: (lv_to_3_0= ruleChannelInterfaceReference )
            	    // InternalFMEDALanguage.g:665:6: lv_to_3_0= ruleChannelInterfaceReference
            	    {

            	    						newCompositeNode(grammarAccess.getChannelAccess().getToChannelInterfaceReferenceParserRuleCall_3_0_0());
            	    					
            	    pushFollow(FOLLOW_19);
            	    lv_to_3_0=ruleChannelInterfaceReference();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getChannelRule());
            	    						}
            	    						add(
            	    							current,
            	    							"to",
            	    							lv_to_3_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.ChannelInterfaceReference");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_4=(Token)match(input,27,FOLLOW_17); 

            	    				newLeafNode(otherlv_4, grammarAccess.getChannelAccess().getCommaKeyword_3_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalFMEDALanguage.g:687:3: ( (lv_to_5_0= ruleChannelInterfaceReference ) )
            // InternalFMEDALanguage.g:688:4: (lv_to_5_0= ruleChannelInterfaceReference )
            {
            // InternalFMEDALanguage.g:688:4: (lv_to_5_0= ruleChannelInterfaceReference )
            // InternalFMEDALanguage.g:689:5: lv_to_5_0= ruleChannelInterfaceReference
            {

            					newCompositeNode(grammarAccess.getChannelAccess().getToChannelInterfaceReferenceParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_13);
            lv_to_5_0=ruleChannelInterfaceReference();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getChannelRule());
            					}
            					add(
            						current,
            						"to",
            						lv_to_5_0,
            						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.ChannelInterfaceReference");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getChannelAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChannel"


    // $ANTLR start "entryRuleFMEDAComponentInstance"
    // InternalFMEDALanguage.g:714:1: entryRuleFMEDAComponentInstance returns [EObject current=null] : iv_ruleFMEDAComponentInstance= ruleFMEDAComponentInstance EOF ;
    public final EObject entryRuleFMEDAComponentInstance() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFMEDAComponentInstance = null;


        try {
            // InternalFMEDALanguage.g:714:63: (iv_ruleFMEDAComponentInstance= ruleFMEDAComponentInstance EOF )
            // InternalFMEDALanguage.g:715:2: iv_ruleFMEDAComponentInstance= ruleFMEDAComponentInstance EOF
            {
             newCompositeNode(grammarAccess.getFMEDAComponentInstanceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFMEDAComponentInstance=ruleFMEDAComponentInstance();

            state._fsp--;

             current =iv_ruleFMEDAComponentInstance; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFMEDAComponentInstance"


    // $ANTLR start "ruleFMEDAComponentInstance"
    // InternalFMEDALanguage.g:721:1: ruleFMEDAComponentInstance returns [EObject current=null] : (otherlv_0= 'subcomponent' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';' ) ;
    public final EObject ruleFMEDAComponentInstance() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:727:2: ( (otherlv_0= 'subcomponent' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';' ) )
            // InternalFMEDALanguage.g:728:2: (otherlv_0= 'subcomponent' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';' )
            {
            // InternalFMEDALanguage.g:728:2: (otherlv_0= 'subcomponent' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';' )
            // InternalFMEDALanguage.g:729:3: otherlv_0= 'subcomponent' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= ';'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getFMEDAComponentInstanceAccess().getSubcomponentKeyword_0());
            		
            // InternalFMEDALanguage.g:733:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFMEDALanguage.g:734:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFMEDALanguage.g:734:4: (lv_name_1_0= RULE_ID )
            // InternalFMEDALanguage.g:735:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_12); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFMEDAComponentInstanceAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFMEDAComponentInstanceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getFMEDAComponentInstanceAccess().getColonKeyword_2());
            		
            // InternalFMEDALanguage.g:755:3: ( (otherlv_3= RULE_ID ) )
            // InternalFMEDALanguage.g:756:4: (otherlv_3= RULE_ID )
            {
            // InternalFMEDALanguage.g:756:4: (otherlv_3= RULE_ID )
            // InternalFMEDALanguage.g:757:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFMEDAComponentInstanceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(otherlv_3, grammarAccess.getFMEDAComponentInstanceAccess().getTypeFMEDAComponentCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getFMEDAComponentInstanceAccess().getSemicolonKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFMEDAComponentInstance"


    // $ANTLR start "entryRuleFMEDADiagnostics"
    // InternalFMEDALanguage.g:776:1: entryRuleFMEDADiagnostics returns [EObject current=null] : iv_ruleFMEDADiagnostics= ruleFMEDADiagnostics EOF ;
    public final EObject entryRuleFMEDADiagnostics() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFMEDADiagnostics = null;


        try {
            // InternalFMEDALanguage.g:776:57: (iv_ruleFMEDADiagnostics= ruleFMEDADiagnostics EOF )
            // InternalFMEDALanguage.g:777:2: iv_ruleFMEDADiagnostics= ruleFMEDADiagnostics EOF
            {
             newCompositeNode(grammarAccess.getFMEDADiagnosticsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFMEDADiagnostics=ruleFMEDADiagnostics();

            state._fsp--;

             current =iv_ruleFMEDADiagnostics; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFMEDADiagnostics"


    // $ANTLR start "ruleFMEDADiagnostics"
    // InternalFMEDALanguage.g:783:1: ruleFMEDADiagnostics returns [EObject current=null] : (otherlv_0= 'diagnostics' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) ;
    public final EObject ruleFMEDADiagnostics() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:789:2: ( (otherlv_0= 'diagnostics' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) )
            // InternalFMEDALanguage.g:790:2: (otherlv_0= 'diagnostics' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
            {
            // InternalFMEDALanguage.g:790:2: (otherlv_0= 'diagnostics' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
            // InternalFMEDALanguage.g:791:3: otherlv_0= 'diagnostics' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getFMEDADiagnosticsAccess().getDiagnosticsKeyword_0());
            		
            // InternalFMEDALanguage.g:795:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFMEDALanguage.g:796:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFMEDALanguage.g:796:4: (lv_name_1_0= RULE_ID )
            // InternalFMEDALanguage.g:797:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFMEDADiagnosticsAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFMEDADiagnosticsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getFMEDADiagnosticsAccess().getSemicolonKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFMEDADiagnostics"


    // $ANTLR start "entryRuleDiagnosticsReference"
    // InternalFMEDALanguage.g:821:1: entryRuleDiagnosticsReference returns [EObject current=null] : iv_ruleDiagnosticsReference= ruleDiagnosticsReference EOF ;
    public final EObject entryRuleDiagnosticsReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDiagnosticsReference = null;


        try {
            // InternalFMEDALanguage.g:821:61: (iv_ruleDiagnosticsReference= ruleDiagnosticsReference EOF )
            // InternalFMEDALanguage.g:822:2: iv_ruleDiagnosticsReference= ruleDiagnosticsReference EOF
            {
             newCompositeNode(grammarAccess.getDiagnosticsReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDiagnosticsReference=ruleDiagnosticsReference();

            state._fsp--;

             current =iv_ruleDiagnosticsReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDiagnosticsReference"


    // $ANTLR start "ruleDiagnosticsReference"
    // InternalFMEDALanguage.g:828:1: ruleDiagnosticsReference returns [EObject current=null] : (otherlv_0= 'diagnosed' otherlv_1= 'by' ( ( (otherlv_2= RULE_ID ) ) otherlv_3= ',' )* ( (otherlv_4= RULE_ID ) ) otherlv_5= 'with' ( (lv_coverage_6_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleDiagnosticsReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_coverage_6_0=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:834:2: ( (otherlv_0= 'diagnosed' otherlv_1= 'by' ( ( (otherlv_2= RULE_ID ) ) otherlv_3= ',' )* ( (otherlv_4= RULE_ID ) ) otherlv_5= 'with' ( (lv_coverage_6_0= RULE_DOUBLE ) ) ) )
            // InternalFMEDALanguage.g:835:2: (otherlv_0= 'diagnosed' otherlv_1= 'by' ( ( (otherlv_2= RULE_ID ) ) otherlv_3= ',' )* ( (otherlv_4= RULE_ID ) ) otherlv_5= 'with' ( (lv_coverage_6_0= RULE_DOUBLE ) ) )
            {
            // InternalFMEDALanguage.g:835:2: (otherlv_0= 'diagnosed' otherlv_1= 'by' ( ( (otherlv_2= RULE_ID ) ) otherlv_3= ',' )* ( (otherlv_4= RULE_ID ) ) otherlv_5= 'with' ( (lv_coverage_6_0= RULE_DOUBLE ) ) )
            // InternalFMEDALanguage.g:836:3: otherlv_0= 'diagnosed' otherlv_1= 'by' ( ( (otherlv_2= RULE_ID ) ) otherlv_3= ',' )* ( (otherlv_4= RULE_ID ) ) otherlv_5= 'with' ( (lv_coverage_6_0= RULE_DOUBLE ) )
            {
            otherlv_0=(Token)match(input,30,FOLLOW_20); 

            			newLeafNode(otherlv_0, grammarAccess.getDiagnosticsReferenceAccess().getDiagnosedKeyword_0());
            		
            otherlv_1=(Token)match(input,31,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getDiagnosticsReferenceAccess().getByKeyword_1());
            		
            // InternalFMEDALanguage.g:844:3: ( ( (otherlv_2= RULE_ID ) ) otherlv_3= ',' )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID) ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1==27) ) {
                        alt8=1;
                    }


                }


                switch (alt8) {
            	case 1 :
            	    // InternalFMEDALanguage.g:845:4: ( (otherlv_2= RULE_ID ) ) otherlv_3= ','
            	    {
            	    // InternalFMEDALanguage.g:845:4: ( (otherlv_2= RULE_ID ) )
            	    // InternalFMEDALanguage.g:846:5: (otherlv_2= RULE_ID )
            	    {
            	    // InternalFMEDALanguage.g:846:5: (otherlv_2= RULE_ID )
            	    // InternalFMEDALanguage.g:847:6: otherlv_2= RULE_ID
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getDiagnosticsReferenceRule());
            	    						}
            	    					
            	    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_19); 

            	    						newLeafNode(otherlv_2, grammarAccess.getDiagnosticsReferenceAccess().getFmedadiagnosticsFMEDADiagnosticsCrossReference_2_0_0());
            	    					

            	    }


            	    }

            	    otherlv_3=(Token)match(input,27,FOLLOW_3); 

            	    				newLeafNode(otherlv_3, grammarAccess.getDiagnosticsReferenceAccess().getCommaKeyword_2_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalFMEDALanguage.g:863:3: ( (otherlv_4= RULE_ID ) )
            // InternalFMEDALanguage.g:864:4: (otherlv_4= RULE_ID )
            {
            // InternalFMEDALanguage.g:864:4: (otherlv_4= RULE_ID )
            // InternalFMEDALanguage.g:865:5: otherlv_4= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDiagnosticsReferenceRule());
            					}
            				
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(otherlv_4, grammarAccess.getDiagnosticsReferenceAccess().getFmedadiagnosticsFMEDADiagnosticsCrossReference_3_0());
            				

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_22); 

            			newLeafNode(otherlv_5, grammarAccess.getDiagnosticsReferenceAccess().getWithKeyword_4());
            		
            // InternalFMEDALanguage.g:880:3: ( (lv_coverage_6_0= RULE_DOUBLE ) )
            // InternalFMEDALanguage.g:881:4: (lv_coverage_6_0= RULE_DOUBLE )
            {
            // InternalFMEDALanguage.g:881:4: (lv_coverage_6_0= RULE_DOUBLE )
            // InternalFMEDALanguage.g:882:5: lv_coverage_6_0= RULE_DOUBLE
            {
            lv_coverage_6_0=(Token)match(input,RULE_DOUBLE,FOLLOW_2); 

            					newLeafNode(lv_coverage_6_0, grammarAccess.getDiagnosticsReferenceAccess().getCoverageDOUBLETerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDiagnosticsReferenceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"coverage",
            						lv_coverage_6_0,
            						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.DOUBLE");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDiagnosticsReference"


    // $ANTLR start "entryRuleFailureModeDef"
    // InternalFMEDALanguage.g:902:1: entryRuleFailureModeDef returns [EObject current=null] : iv_ruleFailureModeDef= ruleFailureModeDef EOF ;
    public final EObject entryRuleFailureModeDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFailureModeDef = null;


        try {
            // InternalFMEDALanguage.g:902:55: (iv_ruleFailureModeDef= ruleFailureModeDef EOF )
            // InternalFMEDALanguage.g:903:2: iv_ruleFailureModeDef= ruleFailureModeDef EOF
            {
             newCompositeNode(grammarAccess.getFailureModeDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFailureModeDef=ruleFailureModeDef();

            state._fsp--;

             current =iv_ruleFailureModeDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFailureModeDef"


    // $ANTLR start "ruleFailureModeDef"
    // InternalFMEDALanguage.g:909:1: ruleFailureModeDef returns [EObject current=null] : (otherlv_0= 'FM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= ';' ) ;
    public final EObject ruleFailureModeDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        EObject lv_diagnostics_2_0 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:915:2: ( (otherlv_0= 'FM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= ';' ) )
            // InternalFMEDALanguage.g:916:2: (otherlv_0= 'FM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= ';' )
            {
            // InternalFMEDALanguage.g:916:2: (otherlv_0= 'FM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= ';' )
            // InternalFMEDALanguage.g:917:3: otherlv_0= 'FM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getFailureModeDefAccess().getFMKeyword_0());
            		
            // InternalFMEDALanguage.g:921:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFMEDALanguage.g:922:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFMEDALanguage.g:922:4: (lv_name_1_0= RULE_ID )
            // InternalFMEDALanguage.g:923:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_23); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFailureModeDefAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFailureModeDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalFMEDALanguage.g:939:3: ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==30) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalFMEDALanguage.g:940:4: (lv_diagnostics_2_0= ruleDiagnosticsReference )
                    {
                    // InternalFMEDALanguage.g:940:4: (lv_diagnostics_2_0= ruleDiagnosticsReference )
                    // InternalFMEDALanguage.g:941:5: lv_diagnostics_2_0= ruleDiagnosticsReference
                    {

                    					newCompositeNode(grammarAccess.getFailureModeDefAccess().getDiagnosticsDiagnosticsReferenceParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_13);
                    lv_diagnostics_2_0=ruleDiagnosticsReference();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFailureModeDefRule());
                    					}
                    					set(
                    						current,
                    						"diagnostics",
                    						lv_diagnostics_2_0,
                    						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.DiagnosticsReference");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getFailureModeDefAccess().getSemicolonKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFailureModeDef"


    // $ANTLR start "entryRulePartFailureModeDef"
    // InternalFMEDALanguage.g:966:1: entryRulePartFailureModeDef returns [EObject current=null] : iv_rulePartFailureModeDef= rulePartFailureModeDef EOF ;
    public final EObject entryRulePartFailureModeDef() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePartFailureModeDef = null;


        try {
            // InternalFMEDALanguage.g:966:59: (iv_rulePartFailureModeDef= rulePartFailureModeDef EOF )
            // InternalFMEDALanguage.g:967:2: iv_rulePartFailureModeDef= rulePartFailureModeDef EOF
            {
             newCompositeNode(grammarAccess.getPartFailureModeDefRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePartFailureModeDef=rulePartFailureModeDef();

            state._fsp--;

             current =iv_rulePartFailureModeDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePartFailureModeDef"


    // $ANTLR start "rulePartFailureModeDef"
    // InternalFMEDALanguage.g:973:1: rulePartFailureModeDef returns [EObject current=null] : (otherlv_0= 'PFM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= 'FR' otherlv_4= '=' ( (lv_failureRate_5_0= RULE_DOUBLE ) ) otherlv_6= ';' ) ;
    public final EObject rulePartFailureModeDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_failureRate_5_0=null;
        Token otherlv_6=null;
        EObject lv_diagnostics_2_0 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:979:2: ( (otherlv_0= 'PFM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= 'FR' otherlv_4= '=' ( (lv_failureRate_5_0= RULE_DOUBLE ) ) otherlv_6= ';' ) )
            // InternalFMEDALanguage.g:980:2: (otherlv_0= 'PFM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= 'FR' otherlv_4= '=' ( (lv_failureRate_5_0= RULE_DOUBLE ) ) otherlv_6= ';' )
            {
            // InternalFMEDALanguage.g:980:2: (otherlv_0= 'PFM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= 'FR' otherlv_4= '=' ( (lv_failureRate_5_0= RULE_DOUBLE ) ) otherlv_6= ';' )
            // InternalFMEDALanguage.g:981:3: otherlv_0= 'PFM' ( (lv_name_1_0= RULE_ID ) ) ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )? otherlv_3= 'FR' otherlv_4= '=' ( (lv_failureRate_5_0= RULE_DOUBLE ) ) otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getPartFailureModeDefAccess().getPFMKeyword_0());
            		
            // InternalFMEDALanguage.g:985:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFMEDALanguage.g:986:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFMEDALanguage.g:986:4: (lv_name_1_0= RULE_ID )
            // InternalFMEDALanguage.g:987:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_24); 

            					newLeafNode(lv_name_1_0, grammarAccess.getPartFailureModeDefAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPartFailureModeDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalFMEDALanguage.g:1003:3: ( (lv_diagnostics_2_0= ruleDiagnosticsReference ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==30) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalFMEDALanguage.g:1004:4: (lv_diagnostics_2_0= ruleDiagnosticsReference )
                    {
                    // InternalFMEDALanguage.g:1004:4: (lv_diagnostics_2_0= ruleDiagnosticsReference )
                    // InternalFMEDALanguage.g:1005:5: lv_diagnostics_2_0= ruleDiagnosticsReference
                    {

                    					newCompositeNode(grammarAccess.getPartFailureModeDefAccess().getDiagnosticsDiagnosticsReferenceParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_25);
                    lv_diagnostics_2_0=ruleDiagnosticsReference();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPartFailureModeDefRule());
                    					}
                    					set(
                    						current,
                    						"diagnostics",
                    						lv_diagnostics_2_0,
                    						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.DiagnosticsReference");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,35,FOLLOW_26); 

            			newLeafNode(otherlv_3, grammarAccess.getPartFailureModeDefAccess().getFRKeyword_3());
            		
            otherlv_4=(Token)match(input,36,FOLLOW_22); 

            			newLeafNode(otherlv_4, grammarAccess.getPartFailureModeDefAccess().getEqualsSignKeyword_4());
            		
            // InternalFMEDALanguage.g:1030:3: ( (lv_failureRate_5_0= RULE_DOUBLE ) )
            // InternalFMEDALanguage.g:1031:4: (lv_failureRate_5_0= RULE_DOUBLE )
            {
            // InternalFMEDALanguage.g:1031:4: (lv_failureRate_5_0= RULE_DOUBLE )
            // InternalFMEDALanguage.g:1032:5: lv_failureRate_5_0= RULE_DOUBLE
            {
            lv_failureRate_5_0=(Token)match(input,RULE_DOUBLE,FOLLOW_13); 

            					newLeafNode(lv_failureRate_5_0, grammarAccess.getPartFailureModeDefAccess().getFailureRateDOUBLETerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPartFailureModeDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"failureRate",
            						lv_failureRate_5_0,
            						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.DOUBLE");
            				

            }


            }

            otherlv_6=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getPartFailureModeDefAccess().getSemicolonKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePartFailureModeDef"


    // $ANTLR start "entryRuleFailureModeReference"
    // InternalFMEDALanguage.g:1056:1: entryRuleFailureModeReference returns [EObject current=null] : iv_ruleFailureModeReference= ruleFailureModeReference EOF ;
    public final EObject entryRuleFailureModeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFailureModeReference = null;


        try {
            // InternalFMEDALanguage.g:1056:61: (iv_ruleFailureModeReference= ruleFailureModeReference EOF )
            // InternalFMEDALanguage.g:1057:2: iv_ruleFailureModeReference= ruleFailureModeReference EOF
            {
             newCompositeNode(grammarAccess.getFailureModeReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFailureModeReference=ruleFailureModeReference();

            state._fsp--;

             current =iv_ruleFailureModeReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFailureModeReference"


    // $ANTLR start "ruleFailureModeReference"
    // InternalFMEDALanguage.g:1063:1: ruleFailureModeReference returns [EObject current=null] : (this_HardwarePartFailureModeReferenceDef_0= ruleHardwarePartFailureModeReferenceDef | this_ThisPartFailureModeReference_1= ruleThisPartFailureModeReference | this_PortFailureModeReference_2= rulePortFailureModeReference | this_ThisPortFailureModeReference_3= ruleThisPortFailureModeReference ) ;
    public final EObject ruleFailureModeReference() throws RecognitionException {
        EObject current = null;

        EObject this_HardwarePartFailureModeReferenceDef_0 = null;

        EObject this_ThisPartFailureModeReference_1 = null;

        EObject this_PortFailureModeReference_2 = null;

        EObject this_ThisPortFailureModeReference_3 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:1069:2: ( (this_HardwarePartFailureModeReferenceDef_0= ruleHardwarePartFailureModeReferenceDef | this_ThisPartFailureModeReference_1= ruleThisPartFailureModeReference | this_PortFailureModeReference_2= rulePortFailureModeReference | this_ThisPortFailureModeReference_3= ruleThisPortFailureModeReference ) )
            // InternalFMEDALanguage.g:1070:2: (this_HardwarePartFailureModeReferenceDef_0= ruleHardwarePartFailureModeReferenceDef | this_ThisPartFailureModeReference_1= ruleThisPartFailureModeReference | this_PortFailureModeReference_2= rulePortFailureModeReference | this_ThisPortFailureModeReference_3= ruleThisPortFailureModeReference )
            {
            // InternalFMEDALanguage.g:1070:2: (this_HardwarePartFailureModeReferenceDef_0= ruleHardwarePartFailureModeReferenceDef | this_ThisPartFailureModeReference_1= ruleThisPartFailureModeReference | this_PortFailureModeReference_2= rulePortFailureModeReference | this_ThisPortFailureModeReference_3= ruleThisPortFailureModeReference )
            int alt11=4;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // InternalFMEDALanguage.g:1071:3: this_HardwarePartFailureModeReferenceDef_0= ruleHardwarePartFailureModeReferenceDef
                    {

                    			newCompositeNode(grammarAccess.getFailureModeReferenceAccess().getHardwarePartFailureModeReferenceDefParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_HardwarePartFailureModeReferenceDef_0=ruleHardwarePartFailureModeReferenceDef();

                    state._fsp--;


                    			current = this_HardwarePartFailureModeReferenceDef_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFMEDALanguage.g:1080:3: this_ThisPartFailureModeReference_1= ruleThisPartFailureModeReference
                    {

                    			newCompositeNode(grammarAccess.getFailureModeReferenceAccess().getThisPartFailureModeReferenceParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ThisPartFailureModeReference_1=ruleThisPartFailureModeReference();

                    state._fsp--;


                    			current = this_ThisPartFailureModeReference_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFMEDALanguage.g:1089:3: this_PortFailureModeReference_2= rulePortFailureModeReference
                    {

                    			newCompositeNode(grammarAccess.getFailureModeReferenceAccess().getPortFailureModeReferenceParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_PortFailureModeReference_2=rulePortFailureModeReference();

                    state._fsp--;


                    			current = this_PortFailureModeReference_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFMEDALanguage.g:1098:3: this_ThisPortFailureModeReference_3= ruleThisPortFailureModeReference
                    {

                    			newCompositeNode(grammarAccess.getFailureModeReferenceAccess().getThisPortFailureModeReferenceParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_ThisPortFailureModeReference_3=ruleThisPortFailureModeReference();

                    state._fsp--;


                    			current = this_ThisPortFailureModeReference_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFailureModeReference"


    // $ANTLR start "entryRuleHardwarePartFailureModeReferenceDef"
    // InternalFMEDALanguage.g:1110:1: entryRuleHardwarePartFailureModeReferenceDef returns [EObject current=null] : iv_ruleHardwarePartFailureModeReferenceDef= ruleHardwarePartFailureModeReferenceDef EOF ;
    public final EObject entryRuleHardwarePartFailureModeReferenceDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHardwarePartFailureModeReferenceDef = null;


        try {
            // InternalFMEDALanguage.g:1110:76: (iv_ruleHardwarePartFailureModeReferenceDef= ruleHardwarePartFailureModeReferenceDef EOF )
            // InternalFMEDALanguage.g:1111:2: iv_ruleHardwarePartFailureModeReferenceDef= ruleHardwarePartFailureModeReferenceDef EOF
            {
             newCompositeNode(grammarAccess.getHardwarePartFailureModeReferenceDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHardwarePartFailureModeReferenceDef=ruleHardwarePartFailureModeReferenceDef();

            state._fsp--;

             current =iv_ruleHardwarePartFailureModeReferenceDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHardwarePartFailureModeReferenceDef"


    // $ANTLR start "ruleHardwarePartFailureModeReferenceDef"
    // InternalFMEDALanguage.g:1117:1: ruleHardwarePartFailureModeReferenceDef returns [EObject current=null] : (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' ) ;
    public final EObject ruleHardwarePartFailureModeReferenceDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:1123:2: ( (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' ) )
            // InternalFMEDALanguage.g:1124:2: (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' )
            {
            // InternalFMEDALanguage.g:1124:2: (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' )
            // InternalFMEDALanguage.g:1125:3: otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getHardwarePartFailureModeReferenceDefAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalFMEDALanguage.g:1129:3: ( (otherlv_1= RULE_ID ) )
            // InternalFMEDALanguage.g:1130:4: (otherlv_1= RULE_ID )
            {
            // InternalFMEDALanguage.g:1130:4: (otherlv_1= RULE_ID )
            // InternalFMEDALanguage.g:1131:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getHardwarePartFailureModeReferenceDefRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_14); 

            					newLeafNode(otherlv_1, grammarAccess.getHardwarePartFailureModeReferenceDefAccess().getPartFMEDAComponentInstanceCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getHardwarePartFailureModeReferenceDefAccess().getFullStopKeyword_2());
            		
            // InternalFMEDALanguage.g:1146:3: ( (otherlv_3= RULE_ID ) )
            // InternalFMEDALanguage.g:1147:4: (otherlv_3= RULE_ID )
            {
            // InternalFMEDALanguage.g:1147:4: (otherlv_3= RULE_ID )
            // InternalFMEDALanguage.g:1148:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getHardwarePartFailureModeReferenceDefRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_15); 

            					newLeafNode(otherlv_3, grammarAccess.getHardwarePartFailureModeReferenceDefAccess().getFailuremodeFailureModeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getHardwarePartFailureModeReferenceDefAccess().getRightSquareBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHardwarePartFailureModeReferenceDef"


    // $ANTLR start "entryRuleThisPartFailureModeReference"
    // InternalFMEDALanguage.g:1167:1: entryRuleThisPartFailureModeReference returns [EObject current=null] : iv_ruleThisPartFailureModeReference= ruleThisPartFailureModeReference EOF ;
    public final EObject entryRuleThisPartFailureModeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisPartFailureModeReference = null;


        try {
            // InternalFMEDALanguage.g:1167:69: (iv_ruleThisPartFailureModeReference= ruleThisPartFailureModeReference EOF )
            // InternalFMEDALanguage.g:1168:2: iv_ruleThisPartFailureModeReference= ruleThisPartFailureModeReference EOF
            {
             newCompositeNode(grammarAccess.getThisPartFailureModeReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleThisPartFailureModeReference=ruleThisPartFailureModeReference();

            state._fsp--;

             current =iv_ruleThisPartFailureModeReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleThisPartFailureModeReference"


    // $ANTLR start "ruleThisPartFailureModeReference"
    // InternalFMEDALanguage.g:1174:1: ruleThisPartFailureModeReference returns [EObject current=null] : (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' ) ;
    public final EObject ruleThisPartFailureModeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:1180:2: ( (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' ) )
            // InternalFMEDALanguage.g:1181:2: (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' )
            {
            // InternalFMEDALanguage.g:1181:2: (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']' )
            // InternalFMEDALanguage.g:1182:3: otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getThisPartFailureModeReferenceAccess().getLeftSquareBracketKeyword_0());
            		
            otherlv_1=(Token)match(input,24,FOLLOW_14); 

            			newLeafNode(otherlv_1, grammarAccess.getThisPartFailureModeReferenceAccess().getThisKeyword_1());
            		
            otherlv_2=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getThisPartFailureModeReferenceAccess().getFullStopKeyword_2());
            		
            // InternalFMEDALanguage.g:1194:3: ( (otherlv_3= RULE_ID ) )
            // InternalFMEDALanguage.g:1195:4: (otherlv_3= RULE_ID )
            {
            // InternalFMEDALanguage.g:1195:4: (otherlv_3= RULE_ID )
            // InternalFMEDALanguage.g:1196:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getThisPartFailureModeReferenceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_15); 

            					newLeafNode(otherlv_3, grammarAccess.getThisPartFailureModeReferenceAccess().getFailuremodeFailureModeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getThisPartFailureModeReferenceAccess().getRightSquareBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleThisPartFailureModeReference"


    // $ANTLR start "entryRulePortFailureModeReference"
    // InternalFMEDALanguage.g:1215:1: entryRulePortFailureModeReference returns [EObject current=null] : iv_rulePortFailureModeReference= rulePortFailureModeReference EOF ;
    public final EObject entryRulePortFailureModeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePortFailureModeReference = null;


        try {
            // InternalFMEDALanguage.g:1215:65: (iv_rulePortFailureModeReference= rulePortFailureModeReference EOF )
            // InternalFMEDALanguage.g:1216:2: iv_rulePortFailureModeReference= rulePortFailureModeReference EOF
            {
             newCompositeNode(grammarAccess.getPortFailureModeReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePortFailureModeReference=rulePortFailureModeReference();

            state._fsp--;

             current =iv_rulePortFailureModeReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePortFailureModeReference"


    // $ANTLR start "rulePortFailureModeReference"
    // InternalFMEDALanguage.g:1222:1: rulePortFailureModeReference returns [EObject current=null] : (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']' ) ;
    public final EObject rulePortFailureModeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:1228:2: ( (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']' ) )
            // InternalFMEDALanguage.g:1229:2: (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']' )
            {
            // InternalFMEDALanguage.g:1229:2: (otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']' )
            // InternalFMEDALanguage.g:1230:3: otherlv_0= '[' ( (otherlv_1= RULE_ID ) ) otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getPortFailureModeReferenceAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalFMEDALanguage.g:1234:3: ( (otherlv_1= RULE_ID ) )
            // InternalFMEDALanguage.g:1235:4: (otherlv_1= RULE_ID )
            {
            // InternalFMEDALanguage.g:1235:4: (otherlv_1= RULE_ID )
            // InternalFMEDALanguage.g:1236:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPortFailureModeReferenceRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_14); 

            					newLeafNode(otherlv_1, grammarAccess.getPortFailureModeReferenceAccess().getPartFMEDAComponentInstanceCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getPortFailureModeReferenceAccess().getFullStopKeyword_2());
            		
            // InternalFMEDALanguage.g:1251:3: ( (otherlv_3= RULE_ID ) )
            // InternalFMEDALanguage.g:1252:4: (otherlv_3= RULE_ID )
            {
            // InternalFMEDALanguage.g:1252:4: (otherlv_3= RULE_ID )
            // InternalFMEDALanguage.g:1253:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPortFailureModeReferenceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_14); 

            					newLeafNode(otherlv_3, grammarAccess.getPortFailureModeReferenceAccess().getPortPortCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getPortFailureModeReferenceAccess().getFullStopKeyword_4());
            		
            // InternalFMEDALanguage.g:1268:3: ( (otherlv_5= RULE_ID ) )
            // InternalFMEDALanguage.g:1269:4: (otherlv_5= RULE_ID )
            {
            // InternalFMEDALanguage.g:1269:4: (otherlv_5= RULE_ID )
            // InternalFMEDALanguage.g:1270:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPortFailureModeReferenceRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_15); 

            					newLeafNode(otherlv_5, grammarAccess.getPortFailureModeReferenceAccess().getFailuremodeFailureModeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getPortFailureModeReferenceAccess().getRightSquareBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePortFailureModeReference"


    // $ANTLR start "entryRuleThisPortFailureModeReference"
    // InternalFMEDALanguage.g:1289:1: entryRuleThisPortFailureModeReference returns [EObject current=null] : iv_ruleThisPortFailureModeReference= ruleThisPortFailureModeReference EOF ;
    public final EObject entryRuleThisPortFailureModeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisPortFailureModeReference = null;


        try {
            // InternalFMEDALanguage.g:1289:69: (iv_ruleThisPortFailureModeReference= ruleThisPortFailureModeReference EOF )
            // InternalFMEDALanguage.g:1290:2: iv_ruleThisPortFailureModeReference= ruleThisPortFailureModeReference EOF
            {
             newCompositeNode(grammarAccess.getThisPortFailureModeReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleThisPortFailureModeReference=ruleThisPortFailureModeReference();

            state._fsp--;

             current =iv_ruleThisPortFailureModeReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleThisPortFailureModeReference"


    // $ANTLR start "ruleThisPortFailureModeReference"
    // InternalFMEDALanguage.g:1296:1: ruleThisPortFailureModeReference returns [EObject current=null] : (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']' ) ;
    public final EObject ruleThisPortFailureModeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalFMEDALanguage.g:1302:2: ( (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']' ) )
            // InternalFMEDALanguage.g:1303:2: (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']' )
            {
            // InternalFMEDALanguage.g:1303:2: (otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']' )
            // InternalFMEDALanguage.g:1304:3: otherlv_0= '[' otherlv_1= 'this' otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) otherlv_4= '.' ( (otherlv_5= RULE_ID ) ) otherlv_6= ']'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getThisPortFailureModeReferenceAccess().getLeftSquareBracketKeyword_0());
            		
            otherlv_1=(Token)match(input,24,FOLLOW_14); 

            			newLeafNode(otherlv_1, grammarAccess.getThisPortFailureModeReferenceAccess().getThisKeyword_1());
            		
            otherlv_2=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getThisPortFailureModeReferenceAccess().getFullStopKeyword_2());
            		
            // InternalFMEDALanguage.g:1316:3: ( (otherlv_3= RULE_ID ) )
            // InternalFMEDALanguage.g:1317:4: (otherlv_3= RULE_ID )
            {
            // InternalFMEDALanguage.g:1317:4: (otherlv_3= RULE_ID )
            // InternalFMEDALanguage.g:1318:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getThisPortFailureModeReferenceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_14); 

            					newLeafNode(otherlv_3, grammarAccess.getThisPortFailureModeReferenceAccess().getPortPortCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getThisPortFailureModeReferenceAccess().getFullStopKeyword_4());
            		
            // InternalFMEDALanguage.g:1333:3: ( (otherlv_5= RULE_ID ) )
            // InternalFMEDALanguage.g:1334:4: (otherlv_5= RULE_ID )
            {
            // InternalFMEDALanguage.g:1334:4: (otherlv_5= RULE_ID )
            // InternalFMEDALanguage.g:1335:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getThisPortFailureModeReferenceRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_15); 

            					newLeafNode(otherlv_5, grammarAccess.getThisPortFailureModeReferenceAccess().getFailuremodeFailureModeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getThisPortFailureModeReferenceAccess().getRightSquareBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleThisPortFailureModeReference"


    // $ANTLR start "entryRuleFailurePropagationDef"
    // InternalFMEDALanguage.g:1354:1: entryRuleFailurePropagationDef returns [EObject current=null] : iv_ruleFailurePropagationDef= ruleFailurePropagationDef EOF ;
    public final EObject entryRuleFailurePropagationDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFailurePropagationDef = null;


        try {
            // InternalFMEDALanguage.g:1354:62: (iv_ruleFailurePropagationDef= ruleFailurePropagationDef EOF )
            // InternalFMEDALanguage.g:1355:2: iv_ruleFailurePropagationDef= ruleFailurePropagationDef EOF
            {
             newCompositeNode(grammarAccess.getFailurePropagationDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFailurePropagationDef=ruleFailurePropagationDef();

            state._fsp--;

             current =iv_ruleFailurePropagationDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFailurePropagationDef"


    // $ANTLR start "ruleFailurePropagationDef"
    // InternalFMEDALanguage.g:1361:1: ruleFailurePropagationDef returns [EObject current=null] : (otherlv_0= 'FP' ( (lv_cause_1_0= ruleFailureModeReference ) ) otherlv_2= '->' ( ( (lv_effect_3_0= ruleFailureModeReference ) ) otherlv_4= ',' )* ( (lv_effect_5_0= ruleFailureModeReference ) ) otherlv_6= ';' ) ;
    public final EObject ruleFailurePropagationDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_cause_1_0 = null;

        EObject lv_effect_3_0 = null;

        EObject lv_effect_5_0 = null;



        	enterRule();

        try {
            // InternalFMEDALanguage.g:1367:2: ( (otherlv_0= 'FP' ( (lv_cause_1_0= ruleFailureModeReference ) ) otherlv_2= '->' ( ( (lv_effect_3_0= ruleFailureModeReference ) ) otherlv_4= ',' )* ( (lv_effect_5_0= ruleFailureModeReference ) ) otherlv_6= ';' ) )
            // InternalFMEDALanguage.g:1368:2: (otherlv_0= 'FP' ( (lv_cause_1_0= ruleFailureModeReference ) ) otherlv_2= '->' ( ( (lv_effect_3_0= ruleFailureModeReference ) ) otherlv_4= ',' )* ( (lv_effect_5_0= ruleFailureModeReference ) ) otherlv_6= ';' )
            {
            // InternalFMEDALanguage.g:1368:2: (otherlv_0= 'FP' ( (lv_cause_1_0= ruleFailureModeReference ) ) otherlv_2= '->' ( ( (lv_effect_3_0= ruleFailureModeReference ) ) otherlv_4= ',' )* ( (lv_effect_5_0= ruleFailureModeReference ) ) otherlv_6= ';' )
            // InternalFMEDALanguage.g:1369:3: otherlv_0= 'FP' ( (lv_cause_1_0= ruleFailureModeReference ) ) otherlv_2= '->' ( ( (lv_effect_3_0= ruleFailureModeReference ) ) otherlv_4= ',' )* ( (lv_effect_5_0= ruleFailureModeReference ) ) otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,37,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getFailurePropagationDefAccess().getFPKeyword_0());
            		
            // InternalFMEDALanguage.g:1373:3: ( (lv_cause_1_0= ruleFailureModeReference ) )
            // InternalFMEDALanguage.g:1374:4: (lv_cause_1_0= ruleFailureModeReference )
            {
            // InternalFMEDALanguage.g:1374:4: (lv_cause_1_0= ruleFailureModeReference )
            // InternalFMEDALanguage.g:1375:5: lv_cause_1_0= ruleFailureModeReference
            {

            					newCompositeNode(grammarAccess.getFailurePropagationDefAccess().getCauseFailureModeReferenceParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_27);
            lv_cause_1_0=ruleFailureModeReference();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFailurePropagationDefRule());
            					}
            					set(
            						current,
            						"cause",
            						lv_cause_1_0,
            						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FailureModeReference");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,38,FOLLOW_17); 

            			newLeafNode(otherlv_2, grammarAccess.getFailurePropagationDefAccess().getHyphenMinusGreaterThanSignKeyword_2());
            		
            // InternalFMEDALanguage.g:1396:3: ( ( (lv_effect_3_0= ruleFailureModeReference ) ) otherlv_4= ',' )*
            loop12:
            do {
                int alt12=2;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // InternalFMEDALanguage.g:1397:4: ( (lv_effect_3_0= ruleFailureModeReference ) ) otherlv_4= ','
            	    {
            	    // InternalFMEDALanguage.g:1397:4: ( (lv_effect_3_0= ruleFailureModeReference ) )
            	    // InternalFMEDALanguage.g:1398:5: (lv_effect_3_0= ruleFailureModeReference )
            	    {
            	    // InternalFMEDALanguage.g:1398:5: (lv_effect_3_0= ruleFailureModeReference )
            	    // InternalFMEDALanguage.g:1399:6: lv_effect_3_0= ruleFailureModeReference
            	    {

            	    						newCompositeNode(grammarAccess.getFailurePropagationDefAccess().getEffectFailureModeReferenceParserRuleCall_3_0_0());
            	    					
            	    pushFollow(FOLLOW_19);
            	    lv_effect_3_0=ruleFailureModeReference();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFailurePropagationDefRule());
            	    						}
            	    						add(
            	    							current,
            	    							"effect",
            	    							lv_effect_3_0,
            	    							"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FailureModeReference");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_4=(Token)match(input,27,FOLLOW_17); 

            	    				newLeafNode(otherlv_4, grammarAccess.getFailurePropagationDefAccess().getCommaKeyword_3_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // InternalFMEDALanguage.g:1421:3: ( (lv_effect_5_0= ruleFailureModeReference ) )
            // InternalFMEDALanguage.g:1422:4: (lv_effect_5_0= ruleFailureModeReference )
            {
            // InternalFMEDALanguage.g:1422:4: (lv_effect_5_0= ruleFailureModeReference )
            // InternalFMEDALanguage.g:1423:5: lv_effect_5_0= ruleFailureModeReference
            {

            					newCompositeNode(grammarAccess.getFailurePropagationDefAccess().getEffectFailureModeReferenceParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_13);
            lv_effect_5_0=ruleFailureModeReference();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFailurePropagationDefRule());
            					}
            					add(
            						current,
            						"effect",
            						lv_effect_5_0,
            						"hu.bme.mit.gamma.fmeda.language.FMEDALanguage.FailureModeReference");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getFailurePropagationDefAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFailurePropagationDef"

    // Delegated rules


    protected DFA7 dfa7 = new DFA7(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA12 dfa12 = new DFA12(this);
    static final String dfa_1s = "\14\uffff";
    static final String dfa_2s = "\1\17\1\4\2\27\2\4\2\20\2\26\2\uffff";
    static final String dfa_3s = "\1\17\1\30\2\27\2\4\2\20\2\33\2\uffff";
    static final String dfa_4s = "\12\uffff\1\1\1\2";
    static final String dfa_5s = "\14\uffff}>";
    static final String[] dfa_6s = {
            "\1\1",
            "\1\3\23\uffff\1\2",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\13\4\uffff\1\12",
            "\1\13\4\uffff\1\12",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "()* loopback of 662:3: ( ( (lv_to_3_0= ruleChannelInterfaceReference ) ) otherlv_4= ',' )*";
        }
    }
    static final String dfa_7s = "\1\17\1\4\2\27\2\4\2\20\4\uffff";
    static final String dfa_8s = "\1\17\1\30\2\27\2\4\2\27\4\uffff";
    static final String dfa_9s = "\10\uffff\1\4\1\2\1\1\1\3";
    static final String[] dfa_10s = {
            "\1\1",
            "\1\3\23\uffff\1\2",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\11\6\uffff\1\10",
            "\1\12\6\uffff\1\13",
            "",
            "",
            "",
            ""
    };
    static final char[] dfa_7 = DFA.unpackEncodedStringToUnsignedChars(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final short[] dfa_9 = DFA.unpackEncodedString(dfa_9s);
    static final short[][] dfa_10 = unpackEncodedStringArray(dfa_10s);

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_7;
            this.max = dfa_8;
            this.accept = dfa_9;
            this.special = dfa_5;
            this.transition = dfa_10;
        }
        public String getDescription() {
            return "1070:2: (this_HardwarePartFailureModeReferenceDef_0= ruleHardwarePartFailureModeReferenceDef | this_ThisPartFailureModeReference_1= ruleThisPartFailureModeReference | this_PortFailureModeReference_2= rulePortFailureModeReference | this_ThisPortFailureModeReference_3= ruleThisPortFailureModeReference )";
        }
    }
    static final String dfa_11s = "\22\uffff";
    static final String dfa_12s = "\1\17\1\4\2\27\2\4\2\20\1\4\2\26\1\4\1\20\2\uffff\1\20\2\26";
    static final String dfa_13s = "\1\17\1\30\2\27\2\4\2\27\1\4\2\33\1\4\1\20\2\uffff\1\20\2\33";
    static final String dfa_14s = "\15\uffff\1\1\1\2\3\uffff";
    static final String dfa_15s = "\22\uffff}>";
    static final String[] dfa_16s = {
            "\1\1",
            "\1\2\23\uffff\1\3",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\11\6\uffff\1\10",
            "\1\12\6\uffff\1\13",
            "\1\14",
            "\1\16\4\uffff\1\15",
            "\1\16\4\uffff\1\15",
            "\1\17",
            "\1\20",
            "",
            "",
            "\1\21",
            "\1\16\4\uffff\1\15",
            "\1\16\4\uffff\1\15"
    };

    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final char[] dfa_12 = DFA.unpackEncodedStringToUnsignedChars(dfa_12s);
    static final char[] dfa_13 = DFA.unpackEncodedStringToUnsignedChars(dfa_13s);
    static final short[] dfa_14 = DFA.unpackEncodedString(dfa_14s);
    static final short[] dfa_15 = DFA.unpackEncodedString(dfa_15s);
    static final short[][] dfa_16 = unpackEncodedStringArray(dfa_16s);

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = dfa_11;
            this.eof = dfa_11;
            this.min = dfa_12;
            this.max = dfa_13;
            this.accept = dfa_14;
            this.special = dfa_15;
            this.transition = dfa_16;
        }
        public String getDescription() {
            return "()* loopback of 1396:3: ( ( (lv_effect_3_0= ruleFailureModeReference ) ) otherlv_4= ',' )*";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000020082002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000110000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000002612040000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000200040000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000040400000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000004000000000L});

}