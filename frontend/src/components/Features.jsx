import React from 'react';

const features = [
  {
    icon: '📊',
    title: 'Financial Structuring',
    desc: 'Structured P&L estimates, cash flow forecasting, and debt-capacity models for micro-businesses.',
    tag: 'Future Phase'
  },
  {
    icon: '💡',
    title: 'Hyper-Local Advisory',
    desc: 'Regional market insights, localized supplier recommendations, and demand trend mapping.',
    tag: 'Future Phase'
  },
  {
    icon: '🏦',
    title: 'Credit Readiness',
    desc: 'Automated documentation checklist & loan eligibility assessment for formal banking schemes.',
    tag: 'Future Phase'
  },
  {
    icon: '🗣️',
    title: 'Vernacular Interaction',
    desc: 'Multilingual AI voice & text assistance tailored for rural micro-entrepreneurs.',
    tag: 'Future Phase'
  }
];

export default function Features() {
  return (
    <div id="architecture" style={{ marginTop: '2rem' }}>
      <h3 className="section-title">Core Advisory Capabilities</h3>
      <p className="section-desc">
        Planned architecture components to empower rural enterprises in subsequent steps.
      </p>

      <div className="features-grid">
        {features.map((item, index) => (
          <div key={index} className="feature-card glass-card">
            <div className="feature-icon-wrapper">{item.icon}</div>
            <h4 className="feature-title">{item.title}</h4>
            <p className="feature-desc">{item.desc}</p>
            <span className="feature-tag">{item.tag}</span>
          </div>
        ))}
      </div>
    </div>
  );
}
