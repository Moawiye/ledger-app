# 🚀 Free Deployment Guide for Students

This guide will help you deploy your Ledger App to free hosting using the GitHub Student Developer Pack.

## 🎓 **Step 1: Get GitHub Student Developer Pack**

1. Go to [education.github.com](https://education.github.com)
2. Sign in with your GitHub account
3. Verify your student email
4. Get instant access to free services

## 🌐 **Step 2: Deploy Backend to Railway**

### **Option A: Railway (Recommended - Free Tier)**
1. Go to [railway.app](https://railway.app)
2. Sign in with GitHub
3. Click "New Project" → "Deploy from GitHub repo"
4. Select your ledger-app repository
5. Set environment variables:
   ```
   DATABASE_URL=postgresql://username:password@host:port/database
   JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
   JPA_DDL_AUTO=update
   PORT=8080
   ```

### **Option B: Render (Alternative Free)**
1. Go to [render.com](https://render.com)
2. Sign in with GitHub
3. Click "New" → "Web Service"
4. Connect your repository
5. Set environment variables (same as above)

## 🗄️ **Step 3: Set Up Free PostgreSQL Database**

### **Option A: Supabase (Recommended)**
1. Go to [supabase.com](https://supabase.com)
2. Sign in with GitHub
3. Create new project
4. Get connection string from Settings → Database
5. Use this as your `DATABASE_URL`

### **Option B: Railway PostgreSQL**
1. In Railway, add PostgreSQL service
2. Get connection string from Variables tab
3. Use this as your `DATABASE_URL`

## ⚡ **Step 4: Deploy Frontend to Vercel**

1. Go to [vercel.com](https://vercel.com)
2. Sign in with GitHub
3. Click "New Project"
4. Import your repository
5. Set build settings:
   - **Framework Preset**: Vite
   - **Build Command**: `cd frontend && npm install && npm run build`
   - **Output Directory**: `frontend/dist`
   - **Install Command**: `cd frontend && npm install`

## 🔧 **Step 5: Update Frontend API URL**

After deploying the backend, update the API URL in your frontend:

```javascript
// frontend/src/services/api.js
const API_BASE_URL = 'https://your-backend-url.railway.app/api/ledger';
```

## 🌍 **Step 6: Set CORS in Backend**

Update your backend CORS settings to include your Vercel domain:

```properties
spring.web.cors.allowed-origins=${CORS_ALLOWED_ORIGINS:http://localhost:5173,https://your-app.vercel.app}
```

## 📱 **Step 7: Test Your Deployed App**

1. **Backend**: Test API endpoints
2. **Frontend**: Test the web interface
3. **Database**: Verify data persistence

## 💰 **Cost Breakdown**

| Service | Plan | Cost |
|---------|------|------|
| **Railway** | Free Tier | $0/month |
| **Supabase** | Free Tier | $0/month |
| **Vercel** | Free Tier | $0/month |
| **Total** | | **$0/month** |

## 🚨 **Free Tier Limits**

### **Railway**
- 750 hours/month
- 512MB RAM
- Shared CPU

### **Supabase**
- 500MB database
- 50MB file storage
- 2GB bandwidth

### **Vercel**
- Unlimited deployments
- 100GB bandwidth
- 100GB storage

## 🔄 **Automatic Deployments**

Once set up, your app will automatically deploy when you push to GitHub:

1. **Push code** to main branch
2. **Backend** automatically rebuilds on Railway
3. **Frontend** automatically rebuilds on Vercel
4. **Database** persists your data

## 🆘 **Troubleshooting**

### **Backend Issues**
- Check Railway logs
- Verify environment variables
- Test database connection

### **Frontend Issues**
- Check Vercel build logs
- Verify API URL
- Test CORS settings

### **Database Issues**
- Check connection string
- Verify database exists
- Check user permissions

## 🎯 **Next Steps**

1. **Get GitHub Student Pack**
2. **Deploy backend to Railway**
3. **Set up Supabase database**
4. **Deploy frontend to Vercel**
5. **Test everything works**
6. **Share your live app!**

## 📚 **Useful Links**

- [GitHub Student Pack](https://education.github.com)
- [Railway Documentation](https://docs.railway.app)
- [Supabase Documentation](https://supabase.com/docs)
- [Vercel Documentation](https://vercel.com/docs)

---

**Your app will be completely free forever with these services!** 🎉
