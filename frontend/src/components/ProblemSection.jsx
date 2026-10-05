import React from 'react';

const problems = [
  {
    icon: '📑',
    title: 'Lack of Formal Business Documentation',
    desc: 'Rural micro-entrepreneurs struggle to structure cash flows, sales receipts, and operating ledgers into bankable financial statements.'
  },
  {
    icon: '💸',
    title: 'High Dependency on Unorganized Credit',
    desc: 'Inability to prove debt capacity leads to relying on informal moneylenders with exorbitant interest rates (24%–36% APR).'
  },
  {
    icon: '📍',
    title: 'Limited Market & Supply Chain Visibility',
    desc: 'Businesses operate without hyper-local demand data, leading to overstocked inventory, inefficient raw material sourcing, and low pricing power.'
  },
  {
    icon: '🚫',
    title: 'Complex Institutional Loan Process',
    desc: 'Government schemes (PMEGP, MUDRA, NABARD) exist, but micro-entrepreneurs lack guided assistance to prepare loan-ready project reports.'
  }
];

export default function ProblemSection({ onGetStarted }) {
  return (
    <section id="problem" className="section-container" style={{ paddingTop: '4rem', paddingBottom: '3rem' }}>
      <div className="section-header text-center">
        <span className="section-badge">The Rural Challenge</span>
        <h2 className="section-title">Why Rural Micro-Entrepreneurs Face Growth Barriers</h2>
        <p className="section-subtitle">
          Over 63 million micro-enterprises in India power local economies, yet 85%+ lack structured advisory and formal credit access.
        </p>
      </div>

      <div className="problem-grid">
        {problems.map((prob, idx) => (
          <div key={idx} className="problem-card glass-card">
            <div className="problem-icon">{prob.icon}</div>
            <h3>{prob.title}</h3>
            <p>{prob.desc}</p>
          </div>
        ))}
      </div>

      <div className="solution-banner glass-card">
        <div className="solution-content">
          <span className="banner-tag">The VyapaarAI Solution</span>
          <h3>Bridge the Gap with AI-Driven Hyper-Local Structuring</h3>
          <p>
            VyapaarAI acts as an intelligent co-pilot—converting informal operational figures into financial diagnostic reports, DSCR debt readiness scores, and actionable expansion roadmaps.
          </p>
        </div>
        <button className="btn-primary" onClick={onGetStarted}>
          <span>Analyze Your Business Now</span>
          <span>→</span>
        </button>
      </div>
    </section>
  );
}
