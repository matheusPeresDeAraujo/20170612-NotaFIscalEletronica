package layout;

public interface Evento<T> {
	
	public void notificar(T info );
	public void notificarCancelar();

}
