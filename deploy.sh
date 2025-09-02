#!/bin/bash

echo "🚀 Ledger App Deployment Script"
echo "================================"

# Check if git remote exists
if ! git remote get-url origin > /dev/null 2>&1; then
    echo "❌ No GitHub remote found!"
    echo ""
    echo "📋 Please follow these steps:"
    echo "1. Create a new repository on GitHub"
    echo "2. Run: git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git"
    echo "3. Run this script again"
    exit 1
fi

echo "✅ GitHub remote found"
echo ""

# Push to GitHub
echo "📤 Pushing code to GitHub..."
git push -u origin main

if [ $? -eq 0 ]; then
    echo "✅ Code pushed successfully!"
    echo ""
    echo "🎯 Next Steps:"
    echo "=============="
    echo ""
    echo "1. 🌐 Get GitHub Student Pack:"
    echo "   https://education.github.com"
    echo ""
    echo "2. 🚂 Deploy Backend to Railway:"
    echo "   https://railway.app"
    echo "   - Connect your GitHub repo"
    echo "   - Set environment variables"
    echo ""
    echo "3. 🗄️ Set up Supabase Database:"
    echo "   https://supabase.com"
    echo "   - Create new project"
    echo "   - Get connection string"
    echo ""
    echo "4. ⚡ Deploy Frontend to Vercel:"
    echo "   https://vercel.com"
    echo "   - Import your repo"
    echo "   - Set build settings"
    echo ""
    echo "📚 See DEPLOYMENT.md for detailed instructions"
    echo ""
    echo "🎉 Your app will be completely FREE forever!"
else
    echo "❌ Failed to push code"
    echo "Please check your GitHub credentials and try again"
fi
