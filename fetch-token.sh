#!/bin/bash

echo "🔑 Fetching Keycloak token..."

RESPONSE=$(curl -s -w "\nHTTP_STATUS:%{http_code}" \
  -X POST "https://dev-keycloak.santechture.com/realms/QATAR/protocol/openid-connect/token" \
  -d "grant_type=password" \
  -d "client_id=robin-qtr" \
  -d "client_secret=$KEYCLOAK_CLIENT_SECRET" \
  -d "username=$KEYCLOAK_USERNAME" \
  -d "password=$KEYCLOAK_PASSWORD")

echo "$RESPONSE" > response.json
HTTP_STATUS=$(grep "HTTP_STATUS" response.json | cut -d':' -f2)
TOKEN=$(sed '/HTTP_STATUS/d' response.json | jq -r '.access_token')

if [ "$HTTP_STATUS" -ne 200 ] || [ -z "$TOKEN" ] || [ "$TOKEN" == "null" ]; then
  echo "❌ Token fetch failed: $RESPONSE"
  exit 1
else
  echo "✅ Token obtained"
  echo "ACCESS_TOKEN=$TOKEN" > variables.sh
fi
