import React from 'react';

export default function CapabilitiesSection() {
  return (
    <section id="capabilities" className="section-container" style={{ paddingTop: '3rem', paddingBottom: '4rem' }}>
      <div className="section-header text-center">
        <span className="section-badge">Core Engine</span>
        <h2 className="section-title">AI-Powered Business & Financial Structuring</h2>
        <p className="section-subtitle">
          Intelligent technology designed specifically for the unique dynamics of rural and tier-3/4 enterprises.
        </p>
      </div>

      <div className="capabilities-grid">
        {/* Capability 1: AI Agent Architecture */}
        <div className="capability-card glass-card">
          <div className="capability-header">
            <span className="cap-icon">🤖</span>
            <div>
              <h3>Contextual AI Advisory Agent</h3>
              <span className="cap-tag">Autonomous AI Pipeline</span>
            </div>
          </div>
          <p className="cap-desc">
            VyapaarAI's autonomous agent framework parses unstructured business context, evaluates regional trade factors, and formulates step-by-step tactical advice tailored to local market conditions.
          </p>
          <ul className="cap-list">
            <li>✓ Domain-tuned prompt pipelines for micro-retail & agri-business</li>
            <li>✓ Contextual reasoning on working capital & risk management</li>
            <li>✓ Multi-turn interactive strategic query support</li>
          </ul>
        </div>

        {/* Capability 2: Financial Advisory */}
        <div className="capability-card glass-card">
          <div className="capability-header">
            <span className="cap-icon">📈</span>
            <div>
              <h3>Financial Advisory & DSCR Structuring</h3>
              <span className="cap-tag">Credit & Solvency Diagnostics</span>
            </div>
          </div>
          <p className="cap-desc">
            Turns basic sales and expense numbers into formal financial metrics that institutional lenders evaluate during loan underwriting.
          </p>
          <ul className="cap-list">
            <li>✓ Automated Net Monthly Profit & Profit Margin (%) math</li>
            <li>✓ Debt Service Coverage Ratio (DSCR) debt-capacity scoring</li>
            <li>✓ Formal bank loan eligibility & scheme matching (MUDRA / CGTMSE)</li>
          </ul>
        </div>

        {/* Capability 3: Hyper-Local Intelligence */}
        <div className="capability-card glass-card">
          <div className="capability-header">
            <span className="cap-icon">📍</span>
            <div>
              <h3>Hyper-Local Market Intelligence</h3>
              <span className="cap-tag">Geographic & Trade Analytics</span>
            </div>
          </div>
          <p className="cap-desc">
            Provides location-specific trade insights to help rural entrepreneurs optimize pricing, source raw materials efficiently, and identify untapped local demand.
          </p>
          <ul className="cap-list">
            <li>✓ Regional demand density & seasonality mapping</li>
            <li>✓ Supplier price benchmark & inventory buffer guidance</li>
            <li>✓ Competitor cluster & localized market risk analysis</li>
          </ul>
        </div>
      </div>
    </section>
  );
}
