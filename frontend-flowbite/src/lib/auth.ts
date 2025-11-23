// Utilidad para manejar autenticación y roles
export interface User {
  username: string;
  roles: string[];
}

let cachedUser: User | null = null;

export async function getCurrentUser(): Promise<User | null> {
  // Si ya tenemos el usuario en caché, retornarlo
  if (cachedUser) {
    return cachedUser;
  }

  try {
    // Intentar obtener el usuario desde la sesión
    const response = await fetch('http://localhost:8081/api/auth/user', {
      method: 'GET',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (response.ok) {
      const user = await response.json();
      cachedUser = user;
      return user;
    }

    // Si falla, intentar obtener el username desde localStorage (si se guardó después del login)
    const savedUsername = typeof window !== 'undefined' ? localStorage.getItem('currentUsername') : null;
    if (savedUsername) {
      const responseWithUsername = await fetch(`http://localhost:8081/api/auth/user?username=${savedUsername}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      
      if (responseWithUsername.ok) {
        const user = await responseWithUsername.json();
        cachedUser = user;
        return user;
      }
    }
  } catch (error) {
    console.error('Error fetching current user:', error);
  }

  return null;
}

export function clearUserCache() {
  cachedUser = null;
  if (typeof window !== 'undefined') {
    localStorage.removeItem('currentUsername');
  }
}

export function setCurrentUsername(username: string) {
  if (typeof window !== 'undefined') {
    localStorage.setItem('currentUsername', username);
  }
}

