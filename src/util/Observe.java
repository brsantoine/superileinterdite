package util;

public interface Observe {
    public void addObservateur(Observateur o);    
    public void notifierObservateur(Message m);
}