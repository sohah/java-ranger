# this is running the valid properties and using mutation for them

alias runDiscovery='LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/home/soha/git/ranger-discovery/lib TARGET_CLASSPATH_WALA=/home/soha/git/ranger-discovery/src/examples/ java -Djava.library.path=/home/soha/git/ranger-discovery/lib -Xmx12288m -ea -Dfile.encoding=UTF-8 -jar /home/soha/git/jpf-core/build/RunJPF.jar '

shopt -s expand_aliases

DISCOVERYDIR=/home/soha/git/ranger-discovery

runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop1/prop1.jpf >& $DISCOVERYDIR/logs/Infusion_Prop1.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop2/prop2.jpf >& $DISCOVERYDIR/logs/Infusion_Prop2.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop3/prop3.jpf >& $DISCOVERYDIR/logs/Infusion_Prop3.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop5/prop5.jpf >& $DISCOVERYDIR/logs/Infusion_Prop5.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop6/prop6.jpf >& $DISCOVERYDIR/logs/Infusion_Prop6.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop7/prop7.jpf >& $DISCOVERYDIR/logs/Infusion_Prop7.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop8/prop8.jpf >& $DISCOVERYDIR/logs/Infusion_Prop8.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop9/prop9.jpf >& $DISCOVERYDIR/logs/Infusion_Prop9.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop10/prop10.jpf >& $DISCOVERYDIR/logs/Infusion_Prop10.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop11/prop11.jpf >& $DISCOVERYDIR/logs/Infusion_Prop11.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop12/prop12.jpf >& $DISCOVERYDIR/logs/Infusion_Prop12.log
runDiscovery $DISCOVERYDIR/src/DiscoveryExamples/GPCA_Infusion/Prop13/prop13.jpf >& $DISCOVERYDIR/logs/Infusion_Prop13.log
