# 🚀 Vercel Deployment Guide

## ❌ **Current Issue: Network Error**
Your React app is getting "Network Error" because it can't connect to your Railway backend.

## 🔧 **Fix: Update API URL for Production**

### **Step 1: Get Your Railway URL**
1. Go to your Railway project
2. Copy the URL (e.g., `https://your-app-name.railway.app`)
3. Add `/api/ledger` to it

### **Step 2: Update Frontend Configuration**
1. Open `frontend/src/config/api.js`
2. Replace the production URL with your actual Railway URL:

```javascript
export const API_CONFIG = {
  development: 'http://localhost:8080/api/ledger',
  
  // ⚠️ UPDATE THIS WITH YOUR ACTUAL RAILWAY URL
  production: 'https://YOUR-ACTUAL-RAILWAY-URL.railway.app/api/ledger'
};
```

### **Step 3: Test Locally First**
```bash
cd frontend
npm run dev
```
- Check browser console for API URL
- Should show: `🌐 API Base URL: http://localhost:8080/api/ledger`

### **Step 4: Deploy to Vercel**
1. Go to [vercel.com](vercel.com)
2. Connect your GitHub repo
3. Deploy the `frontend/` folder
4. **Important**: Set environment variable in Vercel:
   - Key: `VITE_API_URL`
   - Value: `https://your-railway-url.railway.app/api/ledger`

## 🌐 **Environment Variables in Vercel**

### **Option A: Vercel Dashboard**
1. Go to your Vercel project
2. Click **"Settings"** → **"Environment Variables"**
3. Add:
   - **Name**: `VITE_API_URL`
   - **Value**: `https://your-railway-url.railway.app/api/ledger`
   - **Environment**: Production

### **Option B: Update Code (Simpler)**
Just edit `frontend/src/config/api.js` and push to GitHub.

## 🧪 **Test Your Deployment**

### **Before Deploying:**
1. ✅ Backend works on Railway: `/api/ledger` returns data
2. ✅ Frontend builds locally: `npm run build`
3. ✅ API config updated with correct Railway URL

### **After Deploying:**
1. ✅ Vercel app loads without errors
2. ✅ No "Network Error" messages
3. ✅ Ledger data displays correctly
4. ✅ Add/delete entries work

## 🔍 **Troubleshooting**

### **Still Getting Network Error?**
1. **Check Railway URL**: Verify `/api/ledger` works in browser
2. **Check CORS**: Backend should allow `*.vercel.app` domains
3. **Check Console**: Look for API URL in browser console
4. **Check Network Tab**: See what URL is being called

### **Common Issues:**
- ❌ Wrong Railway URL in config
- ❌ CORS not configured for Vercel
- ❌ Railway app not running
- ❌ Frontend not rebuilt after config change

## 🎯 **Quick Fix Summary**

1. **Get Railway URL** from your Railway project
2. **Update** `frontend/src/config/api.js` with correct URL
3. **Commit and push** to GitHub
4. **Redeploy** on Vercel
5. **Test** your live app!

**Your app should work perfectly after updating the API URL!** 🚀✨
