import React, { useState } from 'react';

const CATEGORIES = [
  'Retail / Kirana',
  'Agriculture / Agri-tech',
  'Handloom & Textiles',
  'Food Processing',
  'Micro-Manufacturing',
  'Repair & Technical Services',
  'Livestock & Poultry',
  'Other Services'
];

const BUSINESS_GOALS = [
  'Start a new business',
  'Expand existing business',
  'Increase profit',
  'Reduce expenses',
  'Get a loan',
  'Introduce a new product',
  'Improve sales'
];

const CONTACT_PREFERENCES = [
  'WhatsApp',
  'Phone Call',
  'SMS',
  'Email'
];

export default function BusinessProfileForm({ onSubmitSuccess, onCancel }) {
  const [formData, setFormData] = useState({
    entrepreneurName: '',
    location: '',
    contactPreference: 'WhatsApp',
    businessName: '',
    businessCategory: 'Retail / Kirana',
    description: '',
    yearsInBusiness: 2,
    availableInvestment: 50000,
    monthlyRevenue: 75000,
    monthlyExpenses: 45000,
    existingLoan: 0,
    monthlyLoanPayment: 0,
    businessGoal: 'Expand existing business',
    goalDescription: ''
  });

  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [apiError, setApiError] = useState(null);

  const handleChange = (e) => {
    const { name, value, type } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type === 'number' ? (value === '' ? '' : parseFloat(value)) : value
    }));
    // Clear error for this field
    if (errors[name]) {
      setErrors(prev => ({ ...prev, [name]: null }));
    }
  };

  const handleGoalSelect = (goal) => {
    setFormData(prev => ({ ...prev, businessGoal: goal }));
  };

  const validate = () => {
    const errs = {};
    if (!formData.entrepreneurName.trim()) errs.entrepreneurName = 'Entrepreneur name is required';
    if (!formData.location.trim()) errs.location = 'Location (village/town/state) is required';
    if (!formData.businessName.trim()) errs.businessName = 'Business name is required';
    if (!formData.businessCategory) errs.businessCategory = 'Category is required';
    
    if (formData.yearsInBusiness < 0) errs.yearsInBusiness = 'Years cannot be negative';
    if (formData.monthlyRevenue === '' || formData.monthlyRevenue < 0) errs.monthlyRevenue = 'Monthly revenue must be 0 or greater';
    if (formData.monthlyExpenses === '' || formData.monthlyExpenses < 0) errs.monthlyExpenses = 'Monthly expenses must be 0 or greater';
    if (formData.availableInvestment < 0) errs.availableInvestment = 'Investment cannot be negative';
    if (formData.existingLoan < 0) errs.existingLoan = 'Existing loan cannot be negative';
    if (formData.monthlyLoanPayment < 0) errs.monthlyLoanPayment = 'Monthly repayment cannot be negative';

    if (!formData.businessGoal) errs.businessGoal = 'Please select a primary business goal';

    setErrors(errs);
    return Object.keys(errs).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setApiError(null);

    if (!validate()) {
      return;
    }

    setIsSubmitting(true);

    try {
      // Call parent handler which invokes POST /api/business
      await onSubmitSuccess(formData);
    } catch (err) {
      setApiError(err.message || 'Failed to submit profile to backend server');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="section-container" style={{ paddingTop: '2rem', paddingBottom: '4rem' }}>
      <div className="form-header glass-card text-center mb-6">
        <span className="section-badge">Business Profile & Diagnostics Input</span>
        <h2 className="section-title">Enter Your Business Profile</h2>
        <p className="section-subtitle">
          Provide your operational & financial metrics to generate an instant AI advisory report and debt readiness score.
        </p>
      </div>

      {apiError && (
        <div className="error-banner glass-card mb-6">
          <span className="error-icon">⚠️</span>
          <span>{apiError}</span>
        </div>
      )}

      <form onSubmit={handleSubmit} className="profile-form glass-card">
        {/* SECTION 1: Entrepreneur Details */}
        <div className="form-section">
          <h3 className="form-section-title">
            <span className="section-step-num">1</span> Entrepreneur Details
          </h3>
          <div className="form-grid">
            <div className="form-group">
              <label htmlFor="entrepreneurName">Entrepreneur Name *</label>
              <input
                type="text"
                id="entrepreneurName"
                name="entrepreneurName"
                value={formData.entrepreneurName}
                onChange={handleChange}
                placeholder="e.g. Ramesh Kumar"
                className={errors.entrepreneurName ? 'input-error' : ''}
              />
              {errors.entrepreneurName && <span className="field-error">{errors.entrepreneurName}</span>}
            </div>

            <div className="form-group">
              <label htmlFor="location">Location (District / State) *</label>
              <input
                type="text"
                id="location"
                name="location"
                value={formData.location}
                onChange={handleChange}
                placeholder="e.g. Anantapur, Andhra Pradesh"
                className={errors.location ? 'input-error' : ''}
              />
              {errors.location && <span className="field-error">{errors.location}</span>}
            </div>

            <div className="form-group">
              <label htmlFor="contactPreference">Preferred Contact Method</label>
              <select
                id="contactPreference"
                name="contactPreference"
                value={formData.contactPreference}
                onChange={handleChange}
              >
                {CONTACT_PREFERENCES.map(pref => (
                  <option key={pref} value={pref}>{pref}</option>
                ))}
              </select>
            </div>
          </div>
        </div>

        {/* SECTION 2: Business Details */}
        <div className="form-section">
          <h3 className="form-section-title">
            <span className="section-step-num">2</span> Business Details
          </h3>
          <div className="form-grid">
            <div className="form-group">
              <label htmlFor="businessName">Business Name *</label>
              <input
                type="text"
                id="businessName"
                name="businessName"
                value={formData.businessName}
                onChange={handleChange}
                placeholder="e.g. Sri Lakshmi Kirana & General Store"
                className={errors.businessName ? 'input-error' : ''}
              />
              {errors.businessName && <span className="field-error">{errors.businessName}</span>}
            </div>

            <div className="form-group">
              <label htmlFor="businessCategory">Business Category *</label>
              <select
                id="businessCategory"
                name="businessCategory"
                value={formData.businessCategory}
                onChange={handleChange}
              >
                {CATEGORIES.map(cat => (
                  <option key={cat} value={cat}>{cat}</option>
                ))}
              </select>
            </div>

            <div className="form-group">
              <label htmlFor="yearsInBusiness">Years in Operation</label>
              <input
                type="number"
                id="yearsInBusiness"
                name="yearsInBusiness"
                min="0"
                value={formData.yearsInBusiness}
                onChange={handleChange}
                className={errors.yearsInBusiness ? 'input-error' : ''}
              />
              {errors.yearsInBusiness && <span className="field-error">{errors.yearsInBusiness}</span>}
            </div>

            <div className="form-group full-width">
              <label htmlFor="description">Current Business Description</label>
              <textarea
                id="description"
                name="description"
                rows="3"
                value={formData.description}
                onChange={handleChange}
                placeholder="Briefly describe what your business sells, key customers, and daily operations..."
              ></textarea>
            </div>
          </div>
        </div>

        {/* SECTION 3: Financial Details */}
        <div className="form-section">
          <h3 className="form-section-title">
            <span className="section-step-num">3</span> Financial Details (₹ Monthly)
          </h3>
          <div className="form-grid">
            <div className="form-group">
              <label htmlFor="monthlyRevenue">Monthly Revenue (₹) *</label>
              <div className="input-currency-wrapper">
                <span className="currency-symbol">₹</span>
                <input
                  type="number"
                  id="monthlyRevenue"
                  name="monthlyRevenue"
                  min="0"
                  value={formData.monthlyRevenue}
                  onChange={handleChange}
                  placeholder="75000"
                  className={errors.monthlyRevenue ? 'input-error' : ''}
                />
              </div>
              {errors.monthlyRevenue && <span className="field-error">{errors.monthlyRevenue}</span>}
            </div>

            <div className="form-group">
              <label htmlFor="monthlyExpenses">Monthly Operating Expenses (₹) *</label>
              <div className="input-currency-wrapper">
                <span className="currency-symbol">₹</span>
                <input
                  type="number"
                  id="monthlyExpenses"
                  name="monthlyExpenses"
                  min="0"
                  value={formData.monthlyExpenses}
                  onChange={handleChange}
                  placeholder="45000"
                  className={errors.monthlyExpenses ? 'input-error' : ''}
                />
              </div>
              {errors.monthlyExpenses && <span className="field-error">{errors.monthlyExpenses}</span>}
            </div>

            <div className="form-group">
              <label htmlFor="availableInvestment">Available Savings / Capital for Investment (₹)</label>
              <div className="input-currency-wrapper">
                <span className="currency-symbol">₹</span>
                <input
                  type="number"
                  id="availableInvestment"
                  name="availableInvestment"
                  min="0"
                  value={formData.availableInvestment}
                  onChange={handleChange}
                  placeholder="50000"
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="existingLoan">Existing Total Debt / Loan Outstanding (₹)</label>
              <div className="input-currency-wrapper">
                <span className="currency-symbol">₹</span>
                <input
                  type="number"
                  id="existingLoan"
                  name="existingLoan"
                  min="0"
                  value={formData.existingLoan}
                  onChange={handleChange}
                  placeholder="0"
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="monthlyLoanPayment">Monthly Loan Repayment / EMI (₹)</label>
              <div className="input-currency-wrapper">
                <span className="currency-symbol">₹</span>
                <input
                  type="number"
                  id="monthlyLoanPayment"
                  name="monthlyLoanPayment"
                  min="0"
                  value={formData.monthlyLoanPayment}
                  onChange={handleChange}
                  placeholder="0"
                />
              </div>
            </div>
          </div>
        </div>

        {/* SECTION 4: Business Goal */}
        <div className="form-section">
          <h3 className="form-section-title">
            <span className="section-step-num">4</span> Primary Business Goal
          </h3>
          <div className="form-group full-width">
            <label>Select Primary Strategic Goal *</label>
            <div className="goals-pill-container">
              {BUSINESS_GOALS.map(goal => (
                <button
                  type="button"
                  key={goal}
                  className={`goal-pill ${formData.businessGoal === goal ? 'active' : ''}`}
                  onClick={() => handleGoalSelect(goal)}
                >
                  {goal}
                </button>
              ))}
            </div>
            {errors.businessGoal && <span className="field-error">{errors.businessGoal}</span>}
          </div>

          <div className="form-group full-width" style={{ marginTop: '1rem' }}>
            <label htmlFor="goalDescription">Describe Your Specific Goal / Requirements</label>
            <textarea
              id="goalDescription"
              name="goalDescription"
              rows="3"
              value={formData.goalDescription}
              onChange={handleChange}
              placeholder="Provide details (e.g., I want to purchase a refrigerated delivery van to sell fresh milk in 3 neighbouring villages)..."
            ></textarea>
          </div>
        </div>

        {/* Submit Actions */}
        <div className="form-actions">
          {onCancel && (
            <button type="button" className="btn-secondary" onClick={onCancel}>
              Cancel
            </button>
          )}
          <button type="submit" className="btn-primary btn-large" disabled={isSubmitting}>
            {isSubmitting ? (
              <span>Processing & Saving...</span>
            ) : (
              <>
                <span>Analyze My Business</span>
                <span>→</span>
              </>
            )}
          </button>
        </div>
      </form>
    </div>
  );
}
