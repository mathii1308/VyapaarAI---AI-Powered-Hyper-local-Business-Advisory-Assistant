import React from 'react';

const steps = [
  {
    step: '01',
    title: 'Input Business Profile',
    desc: 'Enter your basic operational details, location, monthly sales, expenses, and growth targets.',
    icon: '📝'
  },
  {
    step: '02',
    title: 'Financial Diagnostics & DSCR',
    desc: 'Automated algorithms compute profit margins, net monthly cash flow, and Debt Service Coverage Ratio.',
    icon: '🧮'
  },
  {
    step: '03',
    title: 'Hyper-Local Market Analysis',
    desc: 'Gain targeted demand metrics, competitive positioning, and regional supply chain optimization tips.',
    icon: '🗺️'
  },
  {
    step: '04',
    title: 'Credit-Readiness Packaging',
    desc: 'Receive tailored loan eligibility assessments, recommended bank schemes, and a strategic growth roadmap.',
    icon: '📜'
  }
];

export default function HowItWorks() {
  return (
    <section id="how-it-works" className="section-container" style={{ paddingTop: '3rem', paddingBottom: '3rem' }}>
      <div className="section-header text-center">
        <span className="section-badge">Simple 4-Step Process</span>
        <h2 className="section-title">How VyapaarAI Transforms Your Business</h2>
        <p className="section-subtitle">
          From informal bookkeeping to formal financial advisory in under 3 minutes.
        </p>
      </div>

      <div className="steps-grid">
        {steps.map((item, index) => (
          <div key={index} className="step-card glass-card">
            <div className="step-number">{item.step}</div>
            <div className="step-icon">{item.icon}</div>
            <h4>{item.title}</h4>
            <p>{item.desc}</p>
          </div>
        ))}
      </div>
    </section>
  );
}
