// API configuration for Spring Boot backend
export const API_URL = 'http://localhost:8081/api';

export async function fetchStats() {
  try {
    const response = await fetch(`${API_URL}/dashboard/stats`);
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    console.error('Error fetching stats:', error);
  }
  return {
    totalClientes: 0,
    totalVentas: 0,
    totalPlatos: 0,
    pedidosPendientes: 0
  };
}

export async function fetchClientes() {
  try {
    const response = await fetch(`${API_URL}/clientes`);
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    console.error('Error fetching clientes:', error);
  }
  return [];
}

export async function fetchMesas() {
  try {
    const response = await fetch(`${API_URL}/mesas`);
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    console.error('Error fetching mesas:', error);
  }
  return [];
}

export async function fetchPlatos() {
  try {
    const response = await fetch(`${API_URL}/platos`);
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    console.error('Error fetching platos:', error);
  }
  return [];
}

export async function fetchPedidos() {
  try {
    const response = await fetch(`${API_URL}/pedidos`);
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    console.error('Error fetching pedidos:', error);
  }
  return [];
}

export async function fetchFacturas() {
  try {
    const response = await fetch(`${API_URL}/facturas`);
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    console.error('Error fetching facturas:', error);
  }
  return [];
}

export async function fetchInsumos() {
  try {
    const response = await fetch(`${API_URL}/insumos`);
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    console.error('Error fetching insumos:', error);
  }
  return [];
}

// Re-exportar desde auth.ts para mantener compatibilidad
export { getCurrentUser } from './auth.js';



