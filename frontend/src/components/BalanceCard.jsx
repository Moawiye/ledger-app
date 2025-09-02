import React from 'react';
import { DollarSign, TrendingUp, TrendingDown, FileText } from 'lucide-react';

const BalanceCard = ({ balance, totalEntries }) => {
  const isPositive = balance >= 0;
  
  return (
    <div className="bg-white rounded-2xl shadow-lg p-6 border border-gray-100">
      <div className="flex items-center justify-between mb-4">
        <div className="flex items-center space-x-2">
          <DollarSign className="w-5 h-5 text-gray-500" />
          <h2 className="text-lg font-semibold text-gray-700">Current Balance</h2>
        </div>
        <div className="flex items-center space-x-1 text-sm text-gray-500">
          <FileText className="w-4 h-4" />
          <span>{totalEntries} entries</span>
        </div>
      </div>
      
      <div className="flex items-center space-x-3">
        {isPositive ? (
          <TrendingUp className="w-8 h-8 text-emerald-500" />
        ) : (
          <TrendingDown className="w-8 h-8 text-red-500" />
        )}
        <div>
          <div className={`text-3xl font-bold ${isPositive ? 'text-emerald-600' : 'text-red-600'}`}>
            {formatCurrency(balance)}
          </div>
          <div className="text-sm text-gray-500 mt-1">
            {isPositive ? 'Positive balance' : 'Negative balance'}
          </div>
        </div>
      </div>
    </div>
  );
};

// Utility function to format currency
const formatCurrency = (cents) => {
  const dollars = cents / 100;
  return new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
  }).format(dollars);
};

export default BalanceCard;
