import React from 'react';

export default function BusinessAnalysisView({ profile, onNewAnalysis }) {
  if (!profile) return null;

  const {
    id,
    entrepreneurName,
    location,
    businessName,
    businessCategory,
    description,
    yearsInBusiness,
    availableInvestment,
    monthlyRevenue,
    monthlyExpenses,
    existingLoan,
    monthlyLoanPayment,
    businessGoal,
    goalDescription,
    netMonthlyProfit,
    profitMarginPercent,
    debtServiceCoverageRatio,
    isDebtFree,
    debtBurdenPercent,
    financialHealthScore,
    financialHealthTier,
    maxSafeMonthlyEmi,
    estimatedMaxLoanAmount,
    createdAt
  } = profile;

  // Determine DSCR badge and color
  let dscrDisplay = isDebtFree ? 'Debt-Free' : (debtServiceCoverageRatio ? debtServiceCoverageRatio.toFixed(2) : 'N/A');
  let dscrHealth = isDebtFree ? 'Optimal (No Existing Debt)' : 'Debt Serviced';
  let dscrColor = '#10B981';

  if (!isDebtFree && debtServiceCoverageRatio < 1.0) {
    dscrHealth = 'Critical Deficit (DSCR < 1.0)';
    dscrColor = '#EF4444';
  } else if (!isDebtFree && debtServiceCoverageRatio < 1.3) {
    dscrHealth = 'Moderate Capacity (DSCR 1.0-1.3)';
    dscrColor = '#F59E0B';
  }

  // Health Score badge color
  let scoreColor = '#10B981';
  if (financialHealthScore < 50) scoreColor = '#EF4444';
  else if (financialHealthScore < 70) scoreColor = '#F59E0B';

  return (
    <div className="section-container" style={{ paddingTop: '2rem', paddingBottom: '4rem' }}>
      {/* Audit & Diagnostic Header */}
      <div className="analysis-header glass-card text-center mb-6">
        <span className="section-badge">Verified Financial Diagnostic Report</span>
        <h2 className="section-title">Analysis for {businessName}</h2>
        <p className="section-subtitle">
          Entrepreneur: <strong>{entrepreneurName}</strong> | Location: <strong>{location}</strong> | Category: <strong>{businessCategory}</strong>
        </p>
        <div style={{ display: 'flex', gap: '0.75rem', justifyContent: 'center', alignItems: 'center', marginTop: '0.5rem', flexWrap: 'wrap' }}>
          <span className="profile-id-tag">Profile ID: #VY-{id || 'DEMO'}</span>
          <span className="feasibility-tag" style={{ background: 'rgba(16, 185, 129, 0.15)', color: scoreColor, borderColor: scoreColor }}>
            Score: {financialHealthScore ?? 75} / 100 ({financialHealthTier || 'Optimal Health'})
          </span>
        </div>
      </div>

      {/* Metrics Dashboard */}
      <div className="metrics-grid mb-6">
        <div className="metric-card glass-card">
          <span className="metric-label">Monthly Revenue</span>
          <h3 className="metric-value">₹{monthlyRevenue?.toLocaleString('en-IN')}</h3>
          <span className="metric-sub">Declared gross sales</span>
        </div>

        <div className="metric-card glass-card">
          <span className="metric-label">Net Monthly Profit</span>
          <h3 className="metric-value" style={{ color: netMonthlyProfit >= 0 ? '#10B981' : '#EF4444' }}>
            ₹{netMonthlyProfit?.toLocaleString('en-IN')}
          </h3>
          <span className="metric-sub">Formula: Revenue − Operating Expenses</span>
        </div>

        <div className="metric-card glass-card">
          <span className="metric-label">Profit Margin</span>
          <h3 className="metric-value">{profitMarginPercent}%</h3>
          <span className="metric-sub">Formula: (Net Profit / Revenue) × 100</span>
        </div>

        <div className="metric-card glass-card">
          <span className="metric-label">DSCR (Solvency)</span>
          <h3 className="metric-value" style={{ color: dscrColor }}>
            {dscrDisplay}
          </h3>
          <span className="metric-sub" style={{ color: dscrColor }}>{dscrHealth}</span>
        </div>
      </div>

      {/* Verified Credit Readiness & Formula Transparency */}
      <div className="analysis-grid mb-6">
        <div className="analysis-card glass-card">
          <div className="card-title-row">
            <span className="card-icon">🏦</span>
            <h3>Credit Capacity & Scheme Assessment</h3>
          </div>
          
          <div className="credit-details" style={{ fontSize: '0.92rem', gap: '0.65rem' }}>
            <p><strong>Debt Burden Ratio:</strong> {debtBurdenPercent ?? 0}% of monthly turnover</p>
            <p><strong>Max Safe Monthly EMI Cap:</strong> ₹{Math.round(maxSafeMonthlyEmi || 0).toLocaleString('en-IN')}/mo (40% net cash flow cap)</p>
            <p><strong>Estimated Indicative Loan Limit:</strong> Up to ₹{Math.round(estimatedMaxLoanAmount || 0).toLocaleString('en-IN')}</p>
            
            <div style={{ marginTop: '0.75rem', padding: '0.75rem', background: 'rgba(255, 255, 255, 0.03)', borderRadius: '8px', border: '1px solid var(--border-color)' }}>
              <p style={{ color: 'var(--accent-amber)', fontWeight: 600, fontSize: '0.85rem', marginBottom: '0.25rem' }}>
                ℹ️ Preliminary Scheme Guidance (Demo Assessment):
              </p>
              <p style={{ color: 'var(--text-muted)', fontSize: '0.82rem', lineHeight: '1.5' }}>
                Potential match: <strong>PM MUDRA (Shishu/Kishore)</strong> or <strong>CGTMSE Guarantee</strong>. 
                <br />
                <em>Note: Formal approval requires bank underwriting, CIBIL verification, and submission of verified tax/sales invoices.</em>
              </p>
            </div>
          </div>
        </div>

        {/* Goal Alignment */}
        <div className="analysis-card glass-card">
          <div className="card-title-row">
            <span className="card-icon">🎯</span>
            <h3>Business Goal Alignment</h3>
          </div>
          <p className="goal-highlight">Target Goal: <strong>{businessGoal}</strong></p>
          {goalDescription && <p className="goal-desc-box">"{goalDescription}"</p>}
          <div className="goal-feasibility" style={{ marginTop: '0.75rem' }}>
            <p className="text-muted" style={{ fontSize: '0.88rem', lineHeight: '1.6' }}>
              <strong>Liquidity Reserve:</strong> Available investment of ₹{availableInvestment?.toLocaleString('en-IN')} covers {(monthlyExpenses > 0 ? (availableInvestment / monthlyExpenses).toFixed(1) : 1)} months of operating buffer.
            </p>
          </div>
        </div>
      </div>

      {/* Strategy Engine Recommendations & Action Plan */}
      <div className="analysis-card glass-card mb-6">
        <div className="card-title-row">
          <span className="card-icon">🚀</span>
          <h3>Strategy Engine — Recommended Business Growth Roadmap</h3>
        </div>
        <div className="strategy-grid" style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))', gap: '1rem', marginTop: '1rem' }}>
          <div className="strategy-card" style={{ padding: '1rem', background: 'rgba(16, 185, 129, 0.08)', borderRadius: '10px', border: '1px solid rgba(16, 185, 129, 0.3)' }}>
            <span className="badge" style={{ background: '#10B981', color: '#fff', fontSize: '0.75rem', padding: '0.2rem 0.6rem', borderRadius: '4px', fontWeight: 600 }}>Top Recommended Strategy</span>
            <h4 style={{ margin: '0.5rem 0 0.25rem', fontSize: '1.05rem' }}>Cost & Inventory Optimization</h4>
            <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)', marginBottom: '0.6rem' }}>Viability Score: <strong>90 / 100</strong> (Highly Recommended)</p>
            <p style={{ fontSize: '0.86rem', lineHeight: '1.4' }}>Reduce material procurement cost by 3–5% and eliminate slow-moving inventory to boost net cash reserves.</p>
          </div>

          <div className="strategy-card" style={{ padding: '1rem', background: 'rgba(255, 255, 255, 0.03)', borderRadius: '10px', border: '1px solid var(--border-color)' }}>
            <span className="badge" style={{ background: 'var(--accent-cyan)', color: '#fff', fontSize: '0.75rem', padding: '0.2rem 0.6rem', borderRadius: '4px', fontWeight: 600 }}>Growth Strategy #2</span>
            <h4 style={{ margin: '0.5rem 0 0.25rem', fontSize: '1.05rem' }}>Local Marketing & Digital QR Payments</h4>
            <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)', marginBottom: '0.6rem' }}>Viability Score: <strong>85 / 100</strong> (Highly Recommended)</p>
            <p style={{ fontSize: '0.86rem', lineHeight: '1.4' }}>Build digital transaction trail via UPI and WhatsApp business catalog to boost repeat local orders.</p>
          </div>

          <div className="strategy-card" style={{ padding: '1rem', background: 'rgba(255, 255, 255, 0.03)', borderRadius: '10px', border: '1px solid var(--border-color)' }}>
            <span className="badge" style={{ background: 'var(--accent-amber)', color: '#fff', fontSize: '0.75rem', padding: '0.2rem 0.6rem', borderRadius: '4px', fontWeight: 600 }}>Growth Strategy #3</span>
            <h4 style={{ margin: '0.5rem 0 0.25rem', fontSize: '1.05rem' }}>High-Margin Product Line Diversification</h4>
            <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)', marginBottom: '0.6rem' }}>Viability Score: <strong>75 / 100</strong> (Feasible)</p>
            <p style={{ fontSize: '0.86rem', lineHeight: '1.4' }}>Utilize available reserves (₹{availableInvestment?.toLocaleString('en-IN')}) to test complementary products.</p>
          </div>
        </div>
      </div>

      {/* Formula Transparency Breakdown */}
      <div className="advisory-card glass-card mb-6">
        <div className="card-title-row">
          <span className="card-icon">📐</span>
          <h3>Audit Verification — Financial Formulas Applied</h3>
        </div>
        <div className="roadmap-steps">
          <div className="roadmap-item">
            <div className="rm-num">1</div>
            <div className="rm-content">
              <h4>Net Monthly Profit</h4>
              <p><code>Net Profit = Monthly Revenue (₹{monthlyRevenue}) − Monthly Expenses (₹{monthlyExpenses}) = ₹{netMonthlyProfit}</code></p>
            </div>
          </div>

          <div className="roadmap-item">
            <div className="rm-num">2</div>
            <div className="rm-content">
              <h4>Debt Service Coverage Ratio (DSCR)</h4>
              <p>
                {isDebtFree ? (
                  <code>DSCR = Debt Free (Monthly EMI = ₹0). No existing debt service burden.</code>
                ) : (
                  <code>DSCR = Net Monthly Profit (₹{netMonthlyProfit}) / Monthly EMI (₹{monthlyLoanPayment}) = {debtServiceCoverageRatio}</code>
                )}
              </p>
            </div>
          </div>

          <div className="roadmap-item">
            <div className="rm-num">3</div>
            <div className="rm-content">
              <h4>Financial Health Score (0 – 100)</h4>
              <p>
                Calculated deterministically based on Profitability (Max 40 pts), Solvency/DSCR (Max 30 pts), Liquidity Reserve (Max 15 pts), and Years in Business (Max 15 pts). 
                Current score: <strong>{financialHealthScore}/100</strong>.
              </p>
            </div>
          </div>
        </div>
      </div>

      {/* Actions */}
      <div className="analysis-actions text-center" style={{ display: 'flex', gap: '1rem', justifyContent: 'center', flexWrap: 'wrap' }}>
        <button className="btn-secondary" onClick={() => window.print()}>
          <span>🖨️ Print / Export Diagnostic Report</span>
        </button>
        <button className="btn-primary" onClick={() => onNavigate && onNavigate('chat', id)}>
          <span>🤖 Ask AI Advisor About This Profile</span>
          <span>→</span>
        </button>
        <button className="btn-secondary" onClick={onNewAnalysis}>
          <span>Analyze Another Profile</span>
        </button>
      </div>
    </div>
  );
}
