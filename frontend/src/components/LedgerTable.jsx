import React from 'react';
import { Trash2, FileText } from 'lucide-react';

const LedgerTable = ({ entries, onDeleteEntry }) => {
  if (entries.length === 0) {
    return (
      <div className="bg-white rounded-2xl shadow-lg p-8 border border-gray-100 text-center">
        <FileText className="w-12 h-12 text-gray-300 mx-auto mb-4" />
        <h3 className="text-lg font-medium text-gray-900 mb-2">No entries found</h3>
        <p className="text-gray-500">Add your first entry to get started with your ledger.</p>
      </div>
    );
  }

  return (
    <div className="bg-white rounded-2xl shadow-lg border border-gray-100 overflow-hidden">
      <div className="overflow-x-auto">
        <table className="w-full">
          <thead>
            <tr className="bg-gray-50 border-b border-gray-200">
              <th className="text-left py-4 px-6 text-sm font-semibold text-gray-700">Date</th>
              <th className="text-left py-4 px-6 text-sm font-semibold text-gray-700">Description</th>
              <th className="text-right py-4 px-6 text-sm font-semibold text-gray-700">Amount</th>
              <th className="text-right py-4 px-6 text-sm font-semibold text-gray-700">Balance</th>
              <th className="text-center py-4 px-6 text-sm font-semibold text-gray-700">Actions</th>
            </tr>
          </thead>
          <tbody>
            {entries.map((entry, index) => (
              <tr
                key={entry.id}
                className={`border-b border-gray-100 hover:bg-gray-50 transition-colors duration-150 ${
                  index % 2 === 0 ? 'bg-white' : 'bg-gray-25'
                }`}
              >
                <td className="py-4 px-6 text-sm text-gray-600">
                  {formatDate(entry.date)}
                </td>
                <td className="py-4 px-6 text-sm text-gray-900 font-medium">
                  {entry.description}
                </td>
                <td className={`py-4 px-6 text-sm font-medium text-right ${
                  entry.amount >= 0 ? 'text-emerald-600' : 'text-red-600'
                }`}>
                  {entry.amount >= 0 ? '+' : ''}{formatCurrency(entry.amount)}
                </td>
                <td className={`py-4 px-6 text-sm font-medium text-right ${
                  entry.runningBalance >= 0 ? 'text-emerald-600' : 'text-red-600'
                }`}>
                  {formatCurrency(entry.runningBalance)}
                </td>
                <td className="py-4 px-6 text-center">
                  <button
                    onClick={() => onDeleteEntry(entry.id)}
                    className="inline-flex items-center justify-center w-8 h-8 text-gray-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-colors duration-200"
                    title="Delete entry"
                  >
                    <Trash2 className="w-4 h-4" />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

// Utility functions
const formatCurrency = (cents) => {
  const dollars = cents / 100;
  return new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
  }).format(dollars);
};

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'short',
    day: 'numeric'
  });
};

export default LedgerTable;
