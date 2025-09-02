// API Configuration
// Update this URL when deploying to production
export const API_CONFIG = {
  // Development (localhost)
  development: 'http://localhost:8080/api/ledger',
  
  // Production (Railway) - UPDATE THIS WITH YOUR ACTUAL RAILWAY URL
  production: 'https://your-app-name.railway.app/api/ledger'
};

// Auto-detect environment and use appropriate URL
const isDevelopment = import.meta.env.DEV;
export const API_BASE_URL = isDevelopment ? API_CONFIG.development : API_CONFIG.production;

console.log('🌐 API Base URL:', API_BASE_URL);
console.log('🔧 Environment:', isDevelopment ? 'Development' : 'Production');
