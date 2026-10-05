import React from 'react';

export default function Hero({ onGetStarted }) {
  return (
    <section id="hero" className="hero-section">
      <div className="badge-wrapper">
        <span>🚀</span> Hyper-Local Financial & Advisory Assistant
      </div>

      <h1 className="hero-title">
        Empowering Micro-Entrepreneurs with <span className="gradient-text">VyapaarAI</span>
      </h1>

      <h2 className="hero-tagline">
        Your Intelligent Business Advisory Partner.
      </h2>

      <p className="hero-subtext">
        Automated hyper-local financial structuring, credit readiness assessment, and tailored 
        business advisory engineered specifically for rural and semi-urban micro-enterprises.
      </p>

      <div className="cta-group">
        <button className="btn-primary" onClick={onGetStarted}>
          <span>Get Started</span>
          <span>→</span>
        </button>
        <a href="#health" className="btn-secondary">
          <span>View Backend API Status</span>
        </a>
      </div>
    </section>
  );
}
