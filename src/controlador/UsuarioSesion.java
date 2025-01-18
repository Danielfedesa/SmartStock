package controlador;


import modelo.Usuario;


public class UsuarioSesion {
    private static Usuario usuarioActual;

    // Establece el usuario actualmente en sesión.
    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    // Obtiene el usuario actualmente en sesión.
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // Obtiene el ID del usuario actualmente en sesión.
    public static int getIdUsuarioActual() {
        return usuarioActual != null ? usuarioActual.getIdUsuario() : -1;
    }

    // Obtiene el rol del usuario actualmente en sesión.
    public static String getRolUsuarioActual() {
        return usuarioActual != null ? usuarioActual.getRol() : null;
    }

    // Limpia la sesión del usuario (por ejemplo, al cerrar sesión).
    public static void cerrarSesion() {
        usuarioActual = null;
    }
    
    
}