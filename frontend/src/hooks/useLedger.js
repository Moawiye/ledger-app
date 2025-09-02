import { useState, useEffect } from 'react';
import apiService from '../services/api';

const useLedger = () => {
  const [ledgerData, setLedgerData] = useState({
    entries: [],
    totalBalance: 0,
    totalEntries: 0
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchData = async (filter = 'all') => {
    try {
      setLoading(true);
      let data;
      
      switch (filter) {
        case 'credits':
          const credits = await apiService.getCredits();
          data = { entries: credits, totalBalance: ledgerData.totalBalance, totalEntries: credits.length };
          break;
        case 'debits':
          const debits = await apiService.getDebits();
          data = { entries: debits, totalBalance: ledgerData.totalBalance, totalEntries: debits.length };
          break;
        default:
          data = await apiService.getAllEntries();
      }
      
      setLedgerData(data);
      setError(null);
    } catch (err) {
      setError(err.message);
      console.error('Error fetching ledger data:', err);
    } finally {
      setLoading(false);
    }
  };

  const addEntry = async (entryData) => {
    try {
      await apiService.addEntry(entryData);
      await fetchData(); // Refresh data after adding
    } catch (err) {
      setError(err.message);
      throw err;
    }
  };

  const deleteEntry = async (id) => {
    try {
      await apiService.deleteEntry(id);
      await fetchData(); // Refresh data after deleting
    } catch (err) {
      setError(err.message);
      throw err;
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return {
    ledgerData,
    loading,
    error,
    addEntry,
    deleteEntry,
    refreshData: fetchData
  };
};

export default useLedger;
