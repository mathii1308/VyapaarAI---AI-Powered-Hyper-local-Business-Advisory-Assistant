const API_BASE_URL = 'http://localhost:8081/api';

/**
 * Fetch health check status from Spring Boot backend.
 */
export async function fetchHealthStatus() {
  const startTime = Date.now();
  try {
    const response = await fetch(`${API_BASE_URL}/health`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const latency = Date.now() - startTime;

    if (!response.ok) {
      throw new Error(`Server responded with status: ${response.status}`);
    }

    const data = await response.json();
    return { success: true, data, latency };
  } catch (error) {
    return {
      success: false,
      error: 'Backend offline (Fallback Advisory Engine active)',
      latency: null
    };
  }
}


/**
 * Create a new business profile (POST /api/business)
 */
export async function saveBusinessProfile(profileData) {
  try {
    const response = await fetch(`${API_BASE_URL}/business`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(profileData),
    });

    if (!response.ok) {
      const errBody = await response.json().catch(() => null);
      const msg = errBody?.message || `Failed to save business profile (HTTP ${response.status})`;
      throw new Error(msg);
    }

    const data = await response.json();
    return { success: true, data };
  } catch (error) {
    console.error('Failed to save business profile:', error);
    return {
      success: false,
      error: error.message || 'Network error while saving business profile',
    };
  }
}

/**
 * Fetch a business profile by ID (GET /api/business/{id})
 */
export async function fetchBusinessProfileById(id) {
  try {
    const response = await fetch(`${API_BASE_URL}/business/${id}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error(`Failed to fetch business profile (HTTP ${response.status})`);
    }

    const data = await response.json();
    return { success: true, data };
  } catch (error) {
    console.error('Failed to fetch business profile by ID:', error);
    return {
      success: false,
      error: error.message || 'Error fetching business profile',
    };
  }
}

/**
 * Fetch all business profiles (GET /api/business)
 */
export async function fetchAllBusinessProfiles() {
  try {
    const response = await fetch(`${API_BASE_URL}/business`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error(`Failed to fetch business profiles (HTTP ${response.status})`);
    }

    const data = await response.json();
    return { success: true, data };
  } catch (error) {
    console.error('Failed to fetch business profiles:', error);
    return {
      success: false,
      error: error.message || 'Error fetching business profiles',
    };
  }
}

/**
 * Send a chat message to the AI Advisor endpoint (POST /api/chat)
 */
export async function sendChatMessage({ message, businessProfileId, history }) {
  try {
    const response = await fetch(`${API_BASE_URL}/chat`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        message,
        businessProfileId: businessProfileId ? Number(businessProfileId) : null,
        history: history || [],
      }),
    });

    if (!response.ok) {
      const errBody = await response.json().catch(() => null);
      throw new Error(errBody?.message || `AI service returned error HTTP ${response.status}`);
    }

    const data = await response.json();
    return { success: true, data };
  } catch (error) {
    console.error('Chat API Error:', error);
    return {
      success: false,
      error: 'AI service is temporarily unavailable. Please try again later.',
    };
  }
}
