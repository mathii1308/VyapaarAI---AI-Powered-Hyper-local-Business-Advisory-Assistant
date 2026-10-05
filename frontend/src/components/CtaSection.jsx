import React from 'react';

export default function CtaSection({ onGetStarted }) {
  return (
    <section className="section-container" style={{ paddingBottom: '4rem' }}>
      <div className="cta-banner-wrapper glass-card text-center">
        <div className="cta-glow"></div>
        <span className="cta-badge">Ready to Grow Your Enterprise?</span>
        <h2 className="cta-title">Get Your Free AI Business & Financial Diagnostic Report</h2>
        <p className="cta-subtext">
          Join thousands of rural micro-entrepreneurs structuring their credit readiness and expanding their profits with VyapaarAI.
        </p>
        <div className="cta-actions">
          <button className="btn-primary btn-large" onClick={onGetStarted}>
            <span>Analyze My Business Profile</span>
            <span>→</span>
          </button>
        </div>
      </div>
    </section>
  );
}
