// API Configuration
// For production, set VITE_RAILWAY_URL environment variable in Vercel
export const API_CONFIG = {
  // Development (localhost)
  development: 'http://localhost:8080/api/ledger',
  
  // Production (Railway) - Set VITE_RAILWAY_URL in Vercel environment variables
  production: import.meta.env.VITE_RAILWAY_URL 
    ? `${import.meta.env.VITE_RAILWAY_URL}/api/ledger`
    : 'https://your-actual-railway-url.railway.app/api/ledger'  // ← UPDATE THIS!
};

// Auto-detect environment and use appropriate URL
const isDevelopment = import.meta.env.DEV;
export const API_BASE_URL = isDevelopment ? API_CONFIG.development : API_CONFIG.production;

console.log('🌐 API Base URL:', API_BASE_URL);
console.log('🔧 Environment:', isDevelopment ? 'Development' : 'Production');
console.log('🚂 Railway URL:', import.meta.env.VITE_RAILWAY_URL || 'Not set');
