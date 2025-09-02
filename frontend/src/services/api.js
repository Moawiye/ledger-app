import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/ledger';

const apiService = {
  // Get all entries with running balances
  async getAllEntries() {
    const response = await axios.get(API_BASE_URL);
    return response.data;
  },

  // Add new entry
  async addEntry(entryData) {
    const response = await axios.post(API_BASE_URL, entryData);
    return response.data;
  },

  // Delete entry
  async deleteEntry(id) {
    const response = await axios.delete(`${API_BASE_URL}/${id}`);
    return response.data;
  },

  // Get credits only
  async getCredits() {
    const response = await axios.get(`${API_BASE_URL}/credits`);
    return response.data;
  },

  // Get debits only
  async getDebits() {
    const response = await axios.get(`${API_BASE_URL}/debits`);
    return response.data;
  },

  // Get current balance
  async getCurrentBalance() {
    const response = await axios.get(`${API_BASE_URL}/balance`);
    return response.data;
  }
};

export default apiService;
