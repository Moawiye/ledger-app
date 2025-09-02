import React from 'react';
import { FileText, TrendingUp, TrendingDown } from 'lucide-react';

const FilterBar = ({ currentFilter, onFilterChange }) => {
  const filters = [
    { key: 'all', label: 'All Entries', icon: FileText },
    { key: 'credits', label: 'Deposits', icon: TrendingUp },
    { key: 'debits', label: 'Payments', icon: TrendingDown }
  ];

  return (
    <div className="flex space-x-2">
      {filters.map(({ key, label, icon: Icon }) => (
        <button
          key={key}
          onClick={() => onFilterChange(key)}
          className={`flex items-center space-x-2 px-4 py-2 rounded-lg text-sm font-medium transition-colors duration-200 ${
            currentFilter === key
              ? 'bg-blue-100 text-blue-700 border border-blue-200'
              : 'bg-white text-gray-600 border border-gray-200 hover:bg-gray-50'
          }`}
        >
          <Icon className="w-4 h-4" />
          <span>{label}</span>
        </button>
      ))}
    </div>
  );
};

export default FilterBar;
