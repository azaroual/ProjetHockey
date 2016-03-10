package servlet;

import java.util.Hashtable;

import javax.ejb.LocalBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;








import metier.GardienManagerRemote;
import metier.JouerRemote;
import metier.MatcheManager;
import metier.MatcheManagerRemote;
import metier.TireManagerRemote;


public class EjbLocator { 

private static Context ctx; 

private static EjbLocator instance = new EjbLocator(); 

private EjbLocator() { 

} 

public static EjbLocator getLocator() { 

return instance; 

} 

private <T> T getEjb(Class<T> ejbClass, String beanName) { 

try { 

final Hashtable jndiProperties = new Hashtable(); 

jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming"); 

final Context context = new InitialContext(jndiProperties); 

final String appName = "ProjectHockeyEAR"; 

final String moduleName = "ProjectHockeyEJB"; 

return (T) context.lookup("java:global/" + appName + "/" + moduleName + "/" + beanName + "!" + ejbClass.getName()); 
} catch (NamingException e) { 

return null; 

} 


} 


public JouerRemote getJouerRemote(){
	return getEjb(JouerRemote.class, "Jouer");
}
public MatcheManagerRemote getMatcheRemote(){
	return getEjb(MatcheManagerRemote.class, "MatcheManager");
}
public GardienManagerRemote getGardienManagerRemote(){
	return getEjb(GardienManagerRemote.class, "GardienManager");
}
public GardienManagerRemote getGardienManagerByIdRemote(){
	return getEjb(GardienManagerRemote.class, "GardienManager");
}
public TireManagerRemote insertTireManagerRemote(){
	return getEjb(TireManagerRemote.class, "TireManager");
}


}