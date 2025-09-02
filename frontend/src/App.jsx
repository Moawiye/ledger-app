import React, { useState } from 'react';
import { PlusCircle } from 'lucide-react';
import BalanceCard from './components/BalanceCard';
import LedgerForm from './components/LedgerForm';
import LedgerTable from './components/LedgerTable';
import FilterBar from './components/FilterBar';
import useLedger from './hooks/useLedger';

export default function LedgerApp() {
  const { ledgerData, loading, error, addEntry, deleteEntry, refreshData } = useLedger();
  const [currentFilter, setCurrentFilter] = useState('all');
  const [showForm, setShowForm] = useState(false);

  const handleFilterChange = (filter) => {
    setCurrentFilter(filter);
    refreshData(filter);
  };

  const handleAddEntry = async (entryData) => {
    await addEntry(entryData);
    setShowForm(false);
  };

  const handleDeleteEntry = async (id) => {
    if (window.confirm('Are you sure you want to delete this entry?')) {
      await deleteEntry(id);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mx-auto mb-4"></div>
          <p className="text-gray-600">Loading ledger...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center text-red-600">
          <p className="text-lg font-medium mb-2">Error loading ledger</p>
          <p className="text-sm">{error}</p>
          <button 
            onClick={() => refreshData()}
            className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700"
          >
            Retry
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 p-4 md:p-6 lg:p-8">
      <div className="max-w-7xl mx-auto space-y-6">
        {/* Header */}
        <div className="flex flex-col md:flex-row md:items-center md:justify-between">
          <div>
            <h1 className="text-3xl font-bold text-gray-900 mb-2">Personal Ledger</h1>
            <p className="text-gray-600">Track your income and expenses</p>
          </div>
          
          <div className="mt-4 md:mt-0 flex items-center space-x-4">
            {!showForm && (
              <button
                onClick={() => setShowForm(true)}
                className="inline-flex items-center space-x-2 bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors duration-200 font-medium"
              >
                <PlusCircle className="w-4 h-4" />
                <span>Add Entry</span>
              </button>
            )}
          </div>
        </div>

        {/* Balance Card */}
        <BalanceCard 
          balance={ledgerData.totalBalance} 
          totalEntries={ledgerData.totalEntries} 
        />

        {/* Add Entry Form */}
        {showForm && (
          <LedgerForm 
            onAddEntry={handleAddEntry}
            onCancel={() => setShowForm(false)}
          />
        )}

        {/* Filter Bar */}
        <div className="flex flex-col md:flex-row md:items-center md:justify-between space-y-4 md:space-y-0">
          <FilterBar 
            currentFilter={currentFilter} 
            onFilterChange={handleFilterChange} 
          />
          
          <div className="text-sm text-gray-500">
            Showing {ledgerData.entries.length} of {ledgerData.totalEntries} entries
          </div>
        </div>

        {/* Entries Table */}
        <LedgerTable 
          entries={ledgerData.entries} 
          onDeleteEntry={handleDeleteEntry} 
        />
      </div>
    </div>
  );
}
