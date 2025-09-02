#!/bin/bash

echo "🚂 Railway URL Update Script"
echo "=============================="
echo ""

# Check if we're in the right directory
if [ ! -f "frontend/src/config/api.js" ]; then
    echo "❌ Error: Please run this script from the ledger-app root directory"
    exit 1
fi

echo "📋 Instructions:"
echo "1. Go to your Railway dashboard"
echo "2. Copy your app URL (e.g., https://ledger-app-1234.up.railway.app)"
echo "3. Paste it below (without the /api/ledger part)"
echo ""

# Get the Railway URL from user
read -p "🔗 Enter your Railway URL (e.g., https://ledger-app-1234.up.railway.app): " RAILWAY_URL

if [ -z "$RAILWAY_URL" ]; then
    echo "❌ No URL provided. Exiting."
    exit 1
fi

# Remove trailing slash if present
RAILWAY_URL=${RAILWAY_URL%/}

echo ""
echo "🔧 Updating configuration file..."

# Update the config file
sed -i.bak "s|https://your-actual-railway-url.railway.app|$RAILWAY_URL|g" frontend/src/config/api.js

echo "✅ Configuration updated!"
echo "🌐 Your API will now use: $RAILWAY_URL/api/ledger"
echo ""
echo "📝 Next steps:"
echo "1. Commit and push: git add . && git commit -m 'Update Railway URL' && git push"
echo "2. Vercel will automatically redeploy"
echo "3. Your app should work without network errors!"
echo ""
echo "🔍 To verify the change, check: frontend/src/config/api.js"
