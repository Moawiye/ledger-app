# 🚨 QUICK FIX: Network Error on Vercel

## ❌ **Current Problem:**
Your app shows "Loading ledger" briefly, then shows "Error loading ledger - Network Error"

## 🔍 **Root Cause:**
The frontend is trying to connect to `https://your-actual-railway-url.railway.app` which doesn't exist.

## 🚀 **IMMEDIATE FIX (Choose One):**

### **Option 1: Run the Update Script (Recommended)**
```bash
./update-railway-url.sh
```
- Follow the prompts
- Enter your actual Railway URL
- Script will update the config automatically

### **Option 2: Manual Edit**
1. Open `frontend/src/config/api.js`
2. Find this line:
   ```javascript
   production: 'https://your-actual-railway-url.railway.app/api/ledger'
   ```
3. Replace `your-actual-railway-url.railway.app` with your **actual Railway URL**
4. Example:
   ```javascript
   production: 'https://ledger-app-1234.up.railway.app/api/ledger'
   ```

### **Option 3: Set Environment Variable in Vercel**
1. Go to your Vercel project dashboard
2. Click **"Settings"** → **"Environment Variables"**
3. Add:
   - **Name**: `VITE_RAILWAY_URL`
   - **Value**: `https://your-actual-railway-url.railway.app` (without /api/ledger)
   - **Environment**: Production

## 🔗 **How to Get Your Railway URL:**
1. Go to [railway.app](https://railway.app)
2. Click on your **ledger-app** project
3. Copy the URL shown (e.g., `https://ledger-app-1234.up.railway.app`)
4. **Don't add `/api/ledger`** - the config will add that automatically

## ✅ **After Fixing:**
1. **Commit and push** your changes:
   ```bash
   git add .
   git commit -m "Fix Railway URL for production"
   git push origin main
   ```
2. **Vercel redeploys** automatically
3. **Test your app** - should work without errors!

## 🧪 **Test Your Fix:**
1. Open your Vercel app
2. Check browser console for:
   ```
   🌐 API Base URL: https://your-railway-url.railway.app/api/ledger
   🚂 Railway URL: https://your-railway-url.railway.app
   ```
3. App should load ledger data successfully

## 🆘 **Still Not Working?**
1. **Verify Railway URL** works: Visit `/api/ledger` in browser
2. **Check CORS**: Backend should allow `*.vercel.app`
3. **Check Network Tab**: See what URL is being called
4. **Check Console**: Look for error messages

**This will fix your network error immediately!** 🚀✨
